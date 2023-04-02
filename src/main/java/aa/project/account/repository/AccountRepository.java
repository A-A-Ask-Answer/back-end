package aa.project.account.repository;

import aa.project.account.entity.Account;
import aa.project.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends BaseRepository<Account, Long> {

    boolean existsByLoginId(String loginId);

    boolean existsByKeyword(String keyword);

    Optional<Account> findByLoginId(String loginId);


}
