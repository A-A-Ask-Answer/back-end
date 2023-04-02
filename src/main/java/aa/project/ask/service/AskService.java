package aa.project.ask.service;

import aa.project.account.entity.Account;
import aa.project.account.repository.AccountRepository;
import aa.project.admin.entity.Report;
import aa.project.admin.repository.ReportRepository;
import aa.project.admin.type.ReportProcess;
import aa.project.ask.dto.AskDto;
import aa.project.ask.entity.Ask;
import aa.project.ask.entity.AskRoom;
import aa.project.ask.repository.AskRepository;
import aa.project.ask.repository.AskRoomRepository;
import aa.project.exception.account.AccountErrorCode;
import aa.project.exception.account.AccountException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AskService {
    private final AccountRepository accountRepository;
    private final AskRoomRepository askRoomRepository;
    private final AskRepository askRepository;
    private final ReportRepository reportRepository;

    @Transactional
    public AskDto.AskResponse askSave(HttpSession session, Long roomId, String content, Long askParentId) {
        String loginId = (String) session.getAttribute("ID");
        Account account = accountRepository.findByLoginId(loginId).orElseThrow(() -> new AccountException(AccountErrorCode.NOT_EXSISTS_ACCOUNT)); //질문을 남길려고 로그인 한 사용자
        AskRoom askRoom = askRoomRepository.findById(roomId).orElseThrow(() ->
                new AccountException(AccountErrorCode.NONE_EXSISTS_ROOM)); // 질문할 방

        String randomNickname = existsByRandomNickname(account, askRoom);

        if (askParentId == null) {
            Ask ask = new Ask(content, account, askRoom, randomNickname);
            Ask firstAsk = askRepository.save(ask);
            AskDto.AskResponse firstAskResponse = new AskDto.AskResponse(askRoom.getId(), ask.getId(), account.getGender(), false, content, randomNickname);
            firstAskResponse.inputAskId(firstAsk.getId());
            return firstAskResponse;
        }
        Ask parentAsk = askRepository.findById(askParentId).orElseThrow(() -> new AccountException(AccountErrorCode.NOT_EXSISTS_ASK));
        Ask childAsk = new Ask(content, account, askRoom, randomNickname);
        childAsk.inputAskParentId(parentAsk);
        Ask savedChildAsk = askRepository.save(childAsk);
        AskDto.AskResponse childAskResponse = new AskDto.AskResponse(askRoom.getId(), savedChildAsk.getId(), account.getGender(), false, content, randomNickname);
        childAskResponse.inputAskParentId(parentAsk.getId());
        childAskResponse.inputAskId(savedChildAsk.getId());
        return childAskResponse;
    }

    /**
     * 신고하기
     */
    @Transactional
    public ResponseEntity report(HttpSession session, Long roomId, AskDto.AskReportRequest request) {
        Account account = accountRepository.findByLoginId(String.valueOf(session.getAttribute("ID"))).orElseThrow(() -> new AccountException(AccountErrorCode.NOT_EXSISTS_ACCOUNT));
        AskRoom askRoom = askRoomRepository.findByAccount_IdAndId(account.getId(), roomId).orElseThrow(() -> new AccountException(AccountErrorCode.NONE_EXSISTS_ROOM));
        Ask ask = askRepository.findByAskRoomAndId(askRoom, request.getAskId()).orElseThrow(() -> new AccountException(AccountErrorCode.NOT_EXSISTS_ASK));
        Report report = new Report(account, askRoom, ask, request.getReportContent());
        report.processStatus(ReportProcess.READY);
        reportRepository.save(report);
        return ResponseEntity.ok().build();
    }

    private String existsByRandomNickname(Account account, AskRoom askRoom) {
        String randomNickname = randomNickname();
        Account createAccount = askRoom.getAccount();
        if (createAccount == account) {
            return account.getName();
        }
        if (askRepository.existsByAccountAndAskRoom(account, askRoom)) {
            Ask ask = askRepository.findTop1ByAccountAndAskRoom(account, askRoom).orElseThrow(() -> new AccountException(AccountErrorCode.NOT_EXSISTS_ASK));
            return ask.getRandomNickname();
        }
        if (askRepository.existsByRandomNickname(randomNickname)) {
            existsByRandomNickname(account, askRoom);
        }
        return randomNickname;
    }


    private String randomNickname() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('가', '히')
                .filteredBy(CharacterPredicates.LETTERS)
                .build();
        return generator.generate(8);
    }


}
