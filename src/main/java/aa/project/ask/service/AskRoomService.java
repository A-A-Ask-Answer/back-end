package aa.project.ask.service;

import aa.project.account.entity.Account;
import aa.project.account.repository.AccountRepository;
import aa.project.admin.repository.ReportRepository;
import aa.project.ask.dto.AskDto;
import aa.project.ask.dto.AskRoomDto;
import aa.project.ask.entity.Ask;
import aa.project.ask.entity.AskRoom;
import aa.project.ask.repository.AskRepository;
import aa.project.ask.repository.AskRoomRepository;
import aa.project.exception.account.AccountErrorCode;
import aa.project.exception.account.AccountException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AskRoomService {
    private final AskRoomRepository askRoomRepository;
    private final AccountRepository accountRepository;
    private final AskRepository askRepository;

    private final ReportRepository reportRepository;


    // 질문 방 생성
    @Transactional
    public AskRoomDto.CreateAskRoomResponse createAskRoom(HttpSession session, String title) {
        Account account = accountRepository.findByLoginId(String.valueOf(session.getAttribute("ID"))).orElseThrow(() -> new AccountException(AccountErrorCode.NOT_EXSISTS_ACCOUNT));
        AskRoom savedAskRoom = askRoomRepository.save(new AskRoom(title, account));
        return new AskRoomDto.CreateAskRoomResponse(account.getId(), savedAskRoom.getId(), savedAskRoom.getTitle(), savedAskRoom.getCreatedAt());
    }

    //방 삭제
    @Transactional
    public ResponseEntity deleteAskRoom(HttpSession session, Long roomId, String keyword) {
        Account account = accountRepository.findByLoginId(String.valueOf(session.getAttribute("ID"))).orElseThrow(() -> new AccountException(AccountErrorCode.NOT_EXSISTS_ACCOUNT));
        AskRoom askRoom = askRoomRepository.findByAccount_IdAndId(account.getId(), roomId).orElseThrow(() -> new AccountException(AccountErrorCode.NONE_EXSISTS_ROOM));
        if (!askRoom.getAccount().getKeyword().equals(keyword)) {
            throw new AccountException(AccountErrorCode.NOT_EQUALS_ACCOUNT_KEYWORD);
        }
        //askRoom.deleteAskRoom();
        askRepository.deleteByRoom(askRoom);
        reportRepository.deleteByRoom(askRoom, account);
        askRoomRepository.deleteById(roomId);
        return ResponseEntity.ok().build();
    }

    public List<AskDto.AskListResponse> roomAskList(Long id) {
        AskRoom askRoom = askRoomRepository.findById(id).orElseThrow(() -> new AccountException(AccountErrorCode.NONE_EXSISTS_ROOM));
        List<Ask> parentAsk = askRepository.findAllByAskRoom(askRoom);
        List<AskDto.AskListResponse> responses = new ArrayList<>();
        for (Ask ask : parentAsk) {
            String parentAskNickname;
            if (ask.getIsRealNameOpen() && ask.getAccount() != null) {
                parentAskNickname = ask.getAccount().getName();
            } else {
                parentAskNickname = ask.getRandomNickname();
            }
            Account askAccount = ask.getAccount();
            String gender;
            if (askAccount == null) {
                gender = "알 수 없음";
            } else {
                gender = askAccount.getGender().name();
            }


            AskDto.AskListResponse askListResponse = new AskDto.AskListResponse(ask.getId(), ask.getAskRoom().getId(), ask.getId(),
                    gender, ask.getContent(), parentAskNickname, ask.getCreatedAt());
            List<AskDto.AskChildListResponse> askChildListResponseList = new ArrayList<>();
            List<Ask> childrenAsk = askRepository.findAllByAskParent_id(ask.getId());
            for (Ask childAsk : childrenAsk) {
                String childAskNickname;
                if (childAsk.getIsRealNameOpen() && childAsk.getAccount() != null) {
                    childAskNickname = childAsk.getAccount().getName();
                } else {
                    childAskNickname = childAsk.getRandomNickname();
                }
                Account childAskAccount = childAsk.getAccount();
                String childAskGender;
                if (childAskAccount == null) {
                    childAskGender = "알 수 없음";
                } else {
                    childAskGender = childAskAccount.getGender().name();
                }
                AskDto.AskChildListResponse askChildListResponse = new AskDto.AskChildListResponse(childAsk.getId(), childAsk.getAskRoom().getId(),
                        childAsk.getAskParent().getId(), childAskGender, childAsk.getContent(), childAskNickname, childAsk.getCreatedAt());
                askChildListResponseList.add(askChildListResponse);
            }
            askListResponse.settingAskListResponse(askChildListResponseList);
            responses.add(askListResponse);
        }

        return responses;

    }


}
