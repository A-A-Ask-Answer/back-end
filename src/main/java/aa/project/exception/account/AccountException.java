package aa.project.exception.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountException extends RuntimeException {
    private final AccountErrorCode accountErrorCode;
}
