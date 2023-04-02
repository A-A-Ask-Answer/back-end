package aa.project.ask.repository;

import aa.project.account.entity.Account;
import aa.project.ask.entity.Ask;
import aa.project.ask.entity.AskRoom;
import aa.project.common.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AskRepository extends BaseRepository<Ask, Long> {

    boolean existsByRandomNickname(String nickname);

    boolean existsByAccountAndAskRoom(Account account, AskRoom askRoom);

    Optional<Ask> findTop1ByAccountAndAskRoom(Account account, AskRoom askRoom);

    @Query("SELECT a FROM Ask a " +
            "JOIN FETCH a.askRoom ar " +
            "JOIN FETCH a.askParent ap " +
            "WHERE ap.id = :id AND ar IS NOT NULL")
    List<Ask> findAllByAskParent_id(@Param("id") Long id);

    @Query("SELECT a FROM Ask a " +
            "JOIN FETCH a.askRoom ar " +
            "WHERE ar = :askRoom AND a.askParent IS NULL")
    List<Ask> findAllByAskRoom(@Param("askRoom") AskRoom askRoom);

    Optional<Ask> findByAskRoomAndId(AskRoom askRoom, Long id);

    @Modifying
    @Query("UPDATE Ask a SET a.isRealNameOpen = true where a.account = :account and a.askRoom = :room")
    void updateIsRealNameOpen(@Param("account") Account account, @Param("room") AskRoom askRoom);

    @Modifying
    @Query("UPDATE Ask a SET a.account = null, a.randomNickname = '탈퇴 회원' where a.account = :account")
    void deleteAccountAsk(@Param("account") Account account);

    @Modifying
    @Query("UPDATE Ask a SET a.deletedAt = CURRENT_TIMESTAMP where a.askRoom = :askRoom")
    void deleteByRoom(@Param("askRoom") AskRoom askRoom);

    List<Ask> findAllByAccount(Account account);


}
