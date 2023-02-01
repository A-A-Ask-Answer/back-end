package aa.project.account.service;

import aa.project.account.dto.AccountLoginDto;
import aa.project.account.dto.AccountSaveDto;
import aa.project.account.entity.Account;
import aa.project.account.repository.AccountRepository;
import aa.project.exception.account.AcccountErrorCode;
import aa.project.exception.account.AccountException;
import aa.project.security.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final SHA256 sha256;

    public String newAccountSave(AccountSaveDto.Request request) throws NoSuchAlgorithmException {
        if (accountRepository.existsByKeyWord(request.getKeyword())) {
            throw new AccountException(AcccountErrorCode.DUPLICATE_KEYWORD);
        }
        if (accountRepository.existsByLoginId(request.getLoginId())) {
            throw new AccountException(AcccountErrorCode.DUPLICATE_LOGIN_ID);
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new AccountException(AcccountErrorCode.NOT_EQUALS_PASSWORD_CONFIRM_PASSWORD);
        }
        Account account = request.toEntity(sha256.encrypt(request.getPassword()));

        accountRepository.save(account);


        return "회원가입 성공";
    }

    public String duplicateLoginId(String loginId) {
        if (accountRepository.existsByLoginId(loginId)) {
            throw new AccountException(AcccountErrorCode.DUPLICATE_LOGIN_ID);
        }
        return "사용 가능한 아이디입니다.";
    }

    public String duplicateKeyword(String keyword) {
        if (accountRepository.existsByKeyWord(keyword)) {
            throw new AccountException(AcccountErrorCode.DUPLICATE_KEYWORD);
        }
        return "사용 가능한 키워드입니다.";
    }

    public String login(AccountLoginDto.LoginRequest loginRequest, HttpSession session) throws NoSuchAlgorithmException {
        Account account = accountRepository.findByLoginId(loginRequest.getLoginId()).orElseThrow(() -> new AccountException(AcccountErrorCode.NOT_EQUALS_LOGIN_ID_OR_PASSWORD));
        if (!account.getPassword().equals(sha256.encrypt(loginRequest.getPassword()))) {
            throw new AccountException(AcccountErrorCode.NOT_EQUALS_LOGIN_ID_OR_PASSWORD);
        }
        session.setAttribute("ID", account.getLoginId());
        session.setMaxInactiveInterval(10);
        return "로그인 성공";
    }
}
