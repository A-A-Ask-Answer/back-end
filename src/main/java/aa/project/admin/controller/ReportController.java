package aa.project.admin.controller;

import aa.project.admin.dto.ReportDto;
import aa.project.admin.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/report")
    public Page<ReportDto.ReportListResponse> reportList(Long id, @PageableDefault(size = 10, sort = "createdAt", direction = DESC) Pageable pageable) {
        return reportService.reportList(id, pageable);
    }

    @PutMapping("/report")
    public ResponseEntity reportResult(Long id, @Valid @RequestBody ReportDto.ReportResultResultRequest reportResultResultRequest) {
        return reportService.reportResult(id, reportResultResultRequest);
    }


}
