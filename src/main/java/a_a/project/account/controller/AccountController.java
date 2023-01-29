package a_a.project.account.controller;

import a_a.project.account.config.LoginUser;
import a_a.project.account.dto.AccountLoginDto;
import a_a.project.account.dto.AccountSaveDto;
import a_a.project.account.entity.Account;
import a_a.project.account.repository.AccountRepository;
import a_a.project.account.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/account")
@Api(tags = {"유저 API"})
public class AccountController {
    private final AccountService accountService;

    private final AccountRepository accountRepository;

    @ApiOperation(value = "회원가입")
    @PostMapping("/new")
    public String newAccountSave(@Valid @RequestBody AccountSaveDto.Request request) throws NoSuchAlgorithmException {
        return accountService.newAccountSave(request);
    }

    @ApiOperation(value = "아이디 중복확인")
    @GetMapping("/logind-id/duplicate")
    public String duplicateLoginId(@NotBlank String loginId) {
        return accountService.duplicateLoginId(loginId);
    }

    @ApiOperation(value = "키워드 중복확인")
    @GetMapping("/key-word/duplicate")
    public String duplicateKeyword(@RequestParam @Size(min = 3, max = 5) String keyword) {
        return accountService.duplicateKeyword(keyword);
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login-test")
    public String login(@RequestBody AccountLoginDto.LoginRequest loginRequest, HttpSession httpSession) throws NoSuchAlgorithmException {

        return accountService.login(loginRequest, httpSession);
    }

    @ApiOperation(value = "로그인정보 확인")
    @GetMapping("/login-info")
    public Account loginInfo(@LoginUser AccountLoginDto.Login login) {
        Account account = accountRepository.findByLoginId(login.getLoginId()).orElse(null);
        return account;
    }

    @ApiOperation(value = "로그인정보 확인")
    @GetMapping("/login-delete")
    public void loginDelete(HttpSession httpSession) {
        httpSession.invalidate();
    }


}
