package aa.project.exception.account;

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


    public static ResponseEntity<AccountExceptionResponseEntity> toAccountExceptionResponse(AccountErrorCode accountErrorCode) {
        return ResponseEntity.status(accountErrorCode.getStatus())
                .body(AccountExceptionResponseEntity.builder()
                        .code(accountErrorCode.name())
                        .status(accountErrorCode.getStatus().value())
                        .message(accountErrorCode.getMessage())
                        .error(accountErrorCode.getStatus().name())
                        .build());
    }
}
