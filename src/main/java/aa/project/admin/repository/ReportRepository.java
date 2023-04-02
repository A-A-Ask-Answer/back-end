package aa.project.admin.repository;

import aa.project.account.entity.Account;
import aa.project.admin.entity.Report;
import aa.project.ask.entity.AskRoom;
import aa.project.common.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReportRepository extends BaseRepository<Report, Long> {
    Page<Report> findAll(Pageable pageable);

    @Query("SELECT r FROM Report r " +
            "JOIN FETCH r.account a " +
            "WHERE r.id = :id")
    Optional<Report> findFetchJoin(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Report re SET re.deletedAt = CURRENT_TIMESTAMP WHERE re.askRoom = :askRoom or re.account = :account")
    void deleteByRoom(@Param("askRoom") AskRoom askRoom, @Param("account") Account account);
}
