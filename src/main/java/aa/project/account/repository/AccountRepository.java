package aa.project.account.repository;

import aa.project.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByLoginId(String loginId);

    boolean existsByKeyWord(String keyWord);

    Optional<Account> findByLoginId(String loginId);


}
