package a_a.project.exception.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class AccountExceptionResponseEntity {
    private final LocalDateTime time = LocalDateTime.now();
    private final String code;
    private final String message;
    private final int status;
    private final String error;


    public static ResponseEntity<AccountExceptionResponseEntity> toAccountExceptionResponse(AcccountErrorCode acccountErrorCode) {
        return ResponseEntity.status(acccountErrorCode.getStatus())
                .body(AccountExceptionResponseEntity.builder()
                        .code(acccountErrorCode.name())
                        .status(acccountErrorCode.getStatus().value())
                        .message(acccountErrorCode.getMessage())
                        .error(acccountErrorCode.getStatus().name())
                        .build());
    }
}
