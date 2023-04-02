package aa.project.account.controller;

import aa.project.account.dto.AccountLoginDto;
import aa.project.account.dto.AccountSaveDto;
import aa.project.account.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/keyword/duplicate")
    public String duplicateKeyword(@RequestParam @Size(min = 3, max = 5) String keyword) {
        return accountService.duplicateKeyword(keyword);
    }

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping
    public ResponseEntity deleteAccount(HttpSession session, @RequestParam String keyword) {
        return accountService.deleteAccount(session, keyword);
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public String login(@RequestBody AccountLoginDto.LoginRequest loginRequest, HttpSession httpSession) throws NoSuchAlgorithmException {

        return accountService.login(loginRequest, httpSession);
    }

    @ApiOperation("로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().body("로그아웃");
    }
}
