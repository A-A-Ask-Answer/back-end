package aa.project.ask.dto;

import aa.project.account.entity.type.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public class AskDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AskRequest {

        private Long askParentId;
        @NotBlank
        private String content;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AskResponse {
        private Long roomId;
        private Long askParentId;

        private Long askId;

        private Gender gender;

        private Boolean isRealNameOpen;
        private String content;

        private String randomNickname;

        public AskResponse(Long roomId, Long askId, Gender gender, Boolean isRealNameOpen, String content, String randomNickname) {
            this.roomId = roomId;
            this.askId = askId;
            this.gender = gender;
            this.isRealNameOpen = isRealNameOpen;
            this.content = content;
            this.randomNickname = randomNickname;
        }

        public void inputAskId(Long askId) {
            this.askId = askId;
        }

        public void inputAskParentId(Long id) {
            this.askParentId = id;
        }
    }


    @Getter
    @NoArgsConstructor
    public static class AskListResponse {
        private Long askId;
        private Long roomId;
        private Long askParentId;
        private String gender;
        private String content;

        private String randomNickname;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdAt;
        private List<AskChildListResponse> askListResponses;

        public AskListResponse(Long askId, Long roomId, Long askParentId, String gender, String content, String randomNickname, LocalDateTime createdAt) {
            this.askId = askId;
            this.roomId = roomId;
            this.askParentId = askParentId;
            this.gender = gender;
            this.content = content;
            this.randomNickname = randomNickname;
            this.createdAt = createdAt;
        }

        public void settingAskListResponse(List<AskChildListResponse> askListResponses) {
            this.askListResponses = askListResponses;
        }
    }

    @Getter
    @AllArgsConstructor
    public static class AskChildListResponse {

        private Long askId;
        private Long roomId;
        private Long parentId;
        private String gender;
        private String content;
        private String randomNickname;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AskReportRequest {
        private Long askId;
        private String reportContent;
    }
}
