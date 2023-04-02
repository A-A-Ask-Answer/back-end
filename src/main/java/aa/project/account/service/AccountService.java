package aa.project.account.service;

import aa.project.account.dto.AccountLoginDto;
import aa.project.account.dto.AccountSaveDto;
import aa.project.account.entity.Account;
import aa.project.account.entity.type.Role;
import aa.project.account.repository.AccountRepository;
import aa.project.admin.repository.ReportQueryDslRepository;
import aa.project.ask.entity.Ask;
import aa.project.ask.entity.AskRoom;
import aa.project.ask.repository.AskRepository;
import aa.project.ask.repository.AskRoomRepository;
import aa.project.exception.account.AccountErrorCode;
import aa.project.exception.account.AccountException;
import aa.project.security.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final SHA256 sha256;

    private final AskRoomRepository askRoomRepository;
    private final AskRepository askRepository;
    private final ReportQueryDslRepository reportQueryDslRepository;

    public String newAccountSave(AccountSaveDto.Request request) throws NoSuchAlgorithmException {
        if (accountRepository.existsByKeyword(request.getKeyword())) {
            throw new AccountException(AccountErrorCode.DUPLICATE_KEYWORD);
        }
        if (accountRepository.existsByLoginId(request.getLoginId())) {
            throw new AccountException(AccountErrorCode.DUPLICATE_LOGIN_ID);
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new AccountException(AccountErrorCode.NOT_EQUALS_PASSWORD_CONFIRM_PASSWORD);
        }
        Account account = request.toEntity(sha256.encrypt(request.getPassword()));
        account.createdAccountRole(Role.USER);

        accountRepository.save(account);


        return "회원가입 성공";
    }

    public String duplicateLoginId(String loginId) {
        if (accountRepository.existsByLoginId(loginId)) {
            throw new AccountException(AccountErrorCode.DUPLICATE_LOGIN_ID);
        }
        return "사용 가능한 아이디입니다.";
    }

    public String duplicateKeyword(String keyword) {
        if (accountRepository.existsByKeyword(keyword)) {
            throw new AccountException(AccountErrorCode.DUPLICATE_KEYWORD);
        }
        return "사용 가능한 키워드입니다.";
    }

    public String login(AccountLoginDto.LoginRequest loginRequest, HttpSession session) throws NoSuchAlgorithmException {
        Account account = accountRepository.findByLoginId(loginRequest.getLoginId()).orElseThrow(() -> new AccountException(AccountErrorCode.NOT_EQUALS_LOGIN_ID_OR_PASSWORD));
        if (!account.getPassword().equals(sha256.encrypt(loginRequest.getPassword()))) {
            throw new AccountException(AccountErrorCode.NOT_EQUALS_LOGIN_ID_OR_PASSWORD);
        }
        session.setAttribute("ID", account.getLoginId());
        session.setMaxInactiveInterval(100);
        return "로그인 성공";
    }

    @Transactional
    public ResponseEntity deleteAccount(HttpSession session, String keyword) {

        Account account = accountRepository.findByLoginId(String.valueOf(session.getAttribute("ID"))).orElseThrow(() -> new AccountException(AccountErrorCode.NOT_EXSISTS_ACCOUNT));
        if (!account.getKeyword().equals(keyword)) {
            throw new AccountException(AccountErrorCode.NOT_EQUALS_ACCOUNT_KEYWORD);
        }
        List<Ask> allByAccount = askRepository.findAllByAccount(account);
        AskRoom askRoom = askRoomRepository.findByAccount(account).orElse(null);
        reportQueryDslRepository.deleteReport(allByAccount, askRoom);
        askRepository.deleteAccountAsk(account);

        if (askRoom != null) {
            askRoomRepository.deleteById(askRoom.getId());
        }
        accountRepository.deleteById(account.getId());
        return ResponseEntity.ok().build();
    }
}
