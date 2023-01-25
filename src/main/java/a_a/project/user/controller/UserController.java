package a_a.project.user.controller;

import a_a.project.user.dto.UserSaveDto;
import a_a.project.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/user")
@Api(tags = {"유저 API"})
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/new")
    public String newUserSave(@Valid @RequestBody UserSaveDto.Request request) {
        return userService.newUserSave(request);
    }

    @ApiOperation(value = "아이디 중복확인")
    @GetMapping("/logind-id/duplicate")
    public String duplicateLoginId(@NotBlank String loginId) {
        return userService.duplicateLoginId(loginId);
    }

    @ApiOperation(value = "키워드 중복확인")
    @GetMapping("/key-word/duplicate")
    public String duplicateKeyword(@RequestParam @Size(min = 3, max = 5) String keyword) {
        return userService.duplicateKeyword(keyword);
    }


}
