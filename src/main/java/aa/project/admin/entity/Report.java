package aa.project.admin.entity;

import aa.project.account.entity.Account;
import aa.project.admin.type.ReportProcess;
import aa.project.ask.entity.Ask;
import aa.project.ask.entity.AskRoom;
import aa.project.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Where(clause = "deleted_at is null")
public class Report extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    private Account account;

    @OneToOne(fetch = FetchType.LAZY)
    private AskRoom askRoom;

    @OneToOne(fetch = FetchType.LAZY)
    private Ask ask;

    private String reportContent;

    @Enumerated(EnumType.STRING)
    private ReportProcess reportProcess;

    private String reportResultContent;

    public Report(Account account, AskRoom askRoom, Ask ask, String reportContent) {
        this.account = account;
        this.askRoom = askRoom;
        this.ask = ask;
        this.reportContent = reportContent;
    }

    public void createReportResultContent(String reportContent) {
        this.reportResultContent = reportContent;
    }

    public void processStatus(ReportProcess reportProcess) {
        this.reportProcess = reportProcess;
    }


}
