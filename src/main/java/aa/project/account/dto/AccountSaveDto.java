package aa.project.account.dto;

import aa.project.account.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AccountSaveDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @NotBlank
        private String name;
        @NotBlank
        private String loginId;
        @NotBlank
        private String password;
        @NotBlank
        private String confirmPassword;
        @NotBlank
        @Size(min = 3, max = 5)
        private String keyword;
        @NotBlank
        private String gender;

        public Account toEntity(String password) {
            return new Account(keyword, loginId, password, name);
        }
    }

    public static class Response {

    }

}
