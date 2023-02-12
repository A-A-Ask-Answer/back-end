package aa.project.account.repository;

import aa.project.account.entity.Account;
import aa.project.account.entity.type.Gender;
import aa.project.account.entity.type.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("유저 저장")
    void save() {
        //given
        Account account = new Account("개발자", "test12", "1234", "hoestory", Gender.MALE);
        account.createdAccountRole(Role.USER);
        //when
        Account saveAccount = accountRepository.save(account);
        //then
        System.out.println(saveAccount.getId());
        Assertions.assertThat(saveAccount.getId()).isEqualTo(1L);
    }

}