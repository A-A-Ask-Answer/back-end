package aa.project.admin.service;

import aa.project.account.entity.Account;
import aa.project.account.entity.type.Role;
import aa.project.account.repository.AccountRepository;
import aa.project.admin.dto.ReportDto;
import aa.project.admin.entity.Report;
import aa.project.admin.repository.ReportRepository;
import aa.project.admin.type.ReportProcess;
import aa.project.ask.repository.AskRepository;
import aa.project.exception.account.AccountErrorCode;
import aa.project.exception.account.AccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final AccountRepository accountRepository;
    private final AskRepository askRepository;

    public Page<ReportDto.ReportListResponse> reportList(Long id, Pageable pageable) {
        return reportRepository.findAll(pageable).map(
                x -> new ReportDto.ReportListResponse(x.getId(), x.getAskRoom().getId(), x.getAskRoom().getTitle(),
                        x.getAsk().getId(), x.getAsk().getContent(), x.getReportContent(),
                        x.getCreatedAt(), x.getReportProcess())
        );
    }

    @Transactional
    public ResponseEntity reportResult(Long id, ReportDto.ReportResultResultRequest reportResultResultRequest) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException(AccountErrorCode.NOT_EXSISTS_ACCOUNT));
        if (account.getRole() != Role.ADMIN) {
            throw new AccountException(AccountErrorCode.NOT_ADMIN);
        }
        Report report = reportRepository.findFetchJoin(reportResultResultRequest.getReportId()).orElseThrow(() -> new AccountException(AccountErrorCode.NOT_EXSISTS_REPORT));
        if (report.getReportProcess() != ReportProcess.READY) {
            throw new AccountException(AccountErrorCode.DUPLICATE_REPORT_PROCESS);
        }
        report.processStatus(reportResultResultRequest.getReportProcess());
        if (reportResultResultRequest.getReportProcess() == ReportProcess.REFUSE) {
            report.createReportResultContent(reportResultResultRequest.getRefuseReason());
        } else if (reportResultResultRequest.getReportProcess() == ReportProcess.APPROVE) {
            report.createReportResultContent("승인");
            Account reportedAccount = report.getAsk().getAccount();
            askRepository.updateIsRealNameOpen(reportedAccount, report.getAskRoom());
        }

        return ResponseEntity.ok().build();
    }
}
