package aa.project.ask.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AskRoomDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateAskRoomRequest {
        @NotBlank
        private String title;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateAskRoomResponse {
        private Long createdAccountId;
        private Long askRoomId;
        private String title;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDateTime createdAt;

    }
}
