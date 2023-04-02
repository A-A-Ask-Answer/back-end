package aa.project.admin.dto;

import aa.project.admin.type.ReportProcess;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class ReportDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ReportListResponse {
        private Long reportId;
        private Long roomId;
        private String roomName;

        private Long askId;

        private String ask;

        private String content;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime reportTime;

        private ReportProcess reportProcess;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReportResultResultRequest {
        @NotNull
        private Long reportId;
        @NotNull
        private ReportProcess reportProcess;

        @NotNull
        @Length
        @Size(min = 3, max = 5)
        private String refuseReason;
    }
}
