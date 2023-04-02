package aa.project.ask.repository;

import aa.project.account.entity.Account;
import aa.project.ask.entity.AskRoom;
import aa.project.common.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AskRoomRepository extends BaseRepository<AskRoom, Long> {

    Optional<AskRoom> findByAccount_IdAndId(Long accountId, Long roomId);

    @Modifying
    @Query("UPDATE AskRoom e SET e.deletedAt = CURRENT_TIMESTAMP, e.account = null where e.id = :id")
    @Override
    void deleteById(@Param("id") Long id);

    Optional<AskRoom> findByAccount(Account account);

}
