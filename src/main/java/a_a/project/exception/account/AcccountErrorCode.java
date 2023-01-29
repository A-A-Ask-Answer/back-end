package a_a.project.exception.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum AcccountErrorCode {

    DUPLICATE_LOGIN_ID(HttpStatus.CONFLICT, "중복된 아이디입니다."),

    DUPLICATE_KEYWORD(HttpStatus.CONFLICT, "중복된 키워드입니다."),


    NOT_EQUALS_PASSWORD_CONFIRM_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호와 비밀번호 재확인이 일치하지 않습니다."),
    NOT_EQUALS_LOGIN_ID_OR_PASSWORD(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호를 잘못입력했습니다.");


    private final HttpStatus status;
    private final String message;
}
