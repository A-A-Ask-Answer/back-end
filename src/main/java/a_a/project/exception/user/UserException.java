package a_a.project.exception.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserException extends RuntimeException{
    private final UserErrorCode userErrorCode;
}
