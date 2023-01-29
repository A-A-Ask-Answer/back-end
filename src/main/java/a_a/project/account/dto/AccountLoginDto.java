package a_a.project.account.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AccountLoginDto {

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class LoginRequest {
        private String loginId;
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class Login {
        private String loginId;
    }
}
