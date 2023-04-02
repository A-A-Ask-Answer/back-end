package aa.project.admin.repository;

import aa.project.ask.entity.Ask;
import aa.project.ask.entity.AskRoom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static aa.project.admin.entity.QReport.report;

@Repository
@RequiredArgsConstructor
public class ReportQueryDslRepository {
    private final JPAQueryFactory queryFactory;


    @Transactional
    public void deleteReport(List<Ask> asks, AskRoom askRoom) {
        queryFactory.update(report)
                .set(report.deletedAt, LocalDateTime.now())
                .where(eqAccount(asks), eqAskRoom(askRoom))
                .execute();
    }

    private BooleanExpression eqAccount(List<Ask> asks) {
        return asks != null ? report.ask.in(asks) : null;
    }

    private BooleanExpression eqAskRoom(AskRoom askRoom) {
        return askRoom != null ? report.askRoom.eq(askRoom) : null;
    }
}
