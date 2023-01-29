package a_a.project.exception.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountException extends RuntimeException {
    private final AcccountErrorCode acccountErrorCode;
}
