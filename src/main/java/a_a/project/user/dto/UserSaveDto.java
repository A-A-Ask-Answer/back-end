package a_a.project.user.dto;

import a_a.project.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserSaveDto {

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

        public User toEntity(String password) {
            return new User(keyword, loginId, password, name);
        }
    }
    
    public static class Response {

    }

}
