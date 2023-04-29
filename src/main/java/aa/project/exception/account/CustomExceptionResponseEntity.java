package aa.project.exception.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class CustomExceptionResponseEntity {
    private final LocalDateTime time = LocalDateTime.now();
    private final String code;
    private final String message;
    private final int status;
    private final String error;


    public static ResponseEntity<CustomExceptionResponseEntity> toAccountExceptionResponse(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getStatus())
                .body(CustomExceptionResponseEntity.builder()
                        .code(errorCode.name())
                        .status(errorCode.getStatus().value())
                        .message(errorCode.getMessage())
                        .error(errorCode.getStatus().name())
                        .build());
    }
}
