package aa.project.exception.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATE_LOGIN_ID_ACCOUNT(HttpStatus.CONFLICT, "중복된 아이디입니다."),

    DUPLICATE_KEYWORD_ACCOUNT(HttpStatus.CONFLICT, "중복된 키워드입니다."),

    NOT_EQUALS_LOGIN_ID_OR_PASSWORD_ACCOUNT(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호를 잘못입력했습니다."),

    NOT_LOGIN_ACCOUNT(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");


    private final HttpStatus status;
    private final String message;
}
