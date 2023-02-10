package aa.project.account.entity;

import aa.project.account.entity.type.Gender;
import aa.project.account.entity.type.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {


    @Test
    @DisplayName("Account 생성자에 값 바인딩하면 유저 권한 USER와 삭제여부 false 반환")
    void accountSave() {
        Account account = new Account("개발자", "test", "1234", "hoestory", Gender.MALE);

        assertThat(account.getPassword()).isEqualTo("1234");
        assertThat(account.getKeyWord()).isEqualTo("개발자");
        assertThat(account.getName()).isEqualTo("hoestory");
        assertThat(account.getLoginId()).isEqualTo("test");
        assertThat(account.getGender()).isEqualTo(Gender.MALE);
        assertThat(account.isActive()).isFalse();
        assertThat(account.getRole()).isEqualTo(Role.USER);
    }
}