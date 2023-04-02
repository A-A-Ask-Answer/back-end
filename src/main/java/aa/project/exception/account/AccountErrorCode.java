package aa.project.exception.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum AccountErrorCode {

    DUPLICATE_LOGIN_ID(HttpStatus.CONFLICT, "중복된 아이디입니다."),

    DUPLICATE_KEYWORD(HttpStatus.CONFLICT, "중복된 키워드입니다."),
    NOT_EQUALS_ACCOUNT_KEYWORD(HttpStatus.BAD_REQUEST, "키워드가 일치하지 않습니다."),

    NONE_EXSISTS_ROOM(HttpStatus.BAD_REQUEST, "질문방이 존재하지 않습니다."),
    NOT_EXSISTS_ACCOUNT(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),

    NOT_EXSISTS_ASK(HttpStatus.NOT_FOUND, "질문이 존재하지 않습니다."),
    NOT_EXSISTS_REPORT(HttpStatus.NOT_FOUND, "신고내용 존재하지 않습니다."),
    DUPLICATE_REPORT_PROCESS(HttpStatus.CONFLICT, "이미 처리된 신고건 입니다."),

    NOT_ADMIN(HttpStatus.UNAUTHORIZED, "관리자가 아닙니다"),
    NOT_EQUALS_PASSWORD_CONFIRM_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호와 비밀번호 재확인이 일치하지 않습니다."),
    NOT_EQUALS_LOGIN_ID_OR_PASSWORD(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호를 잘못입력했습니다.");


    private final HttpStatus status;
    private final String message;
}
