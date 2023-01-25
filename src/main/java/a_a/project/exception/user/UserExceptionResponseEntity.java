package a_a.project.exception.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class UserExceptionResponseEntity {
    private final LocalDateTime time = LocalDateTime.now();
    private final String code;
    private final String message;
    private final int status;
    private final String error;


    public static ResponseEntity<UserExceptionResponseEntity> toUserExceptionResponse(UserErrorCode userErrorCode) {
        return ResponseEntity.status(userErrorCode.getStatus())
                .body(UserExceptionResponseEntity.builder()
                        .code(userErrorCode.name())
                        .status(userErrorCode.getStatus().value())
                        .message(userErrorCode.getMessage())
                        .error(userErrorCode.getStatus().name())
                        .build());
    }
}
