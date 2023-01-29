package a_a.project.account.service;

import a_a.project.account.entity.Account;
import a_a.project.account.repository.AccountRepository;
import a_a.project.exception.account.AccountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class AccountServiceTest {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @BeforeEach
    public void setting() {
        accountRepository.save(new Account("일요일", "test1", "1234", "hoestory"));
    }


    @Test
    @DisplayName("중복된 키워드가 존재할 경우 UserException 예외가 발생합니다.")
    void duplicateKeyword() {
        //given
        String keyword = "일요일";
        //when
        AccountException accountException = assertThrows(AccountException.class, () -> accountService.duplicateKeyword(keyword));
        //then
        assertThat(accountException.getAcccountErrorCode().getMessage()).isEqualTo("중복된 키워드입니다.");
        assertThat(accountException.getAcccountErrorCode().getStatus()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    @DisplayName("중복된 키워드가 아닐경우 '사용 가능한 키워드입니다.' 반환")
    void notDuplicateKeyword() {
        //given
        String keyword = "월요일";
        //when
        String responseKeyword = accountService.duplicateKeyword(keyword);
        //then
        assertThat(responseKeyword).isEqualTo("사용 가능한 키워드입니다.");
    }
}