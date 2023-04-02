package aa.project.ask.controller;

import aa.project.ask.dto.AskDto;
import aa.project.ask.service.AskService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Api(tags = {"질문 및 답글"})
@RestController
@RequestMapping("/api/ask")
@RequiredArgsConstructor
public class AskController {
    private final AskService askService;


    @PostMapping("/{room-id}")
    public AskDto.AskResponse askSave(HttpSession session, @PathVariable("room-id") Long roomId, @RequestBody AskDto.AskRequest askRequest) {
        return askService.askSave(session, roomId, askRequest.getContent(), askRequest.getAskParentId());
    }

    @PostMapping("/report/{room-id}")
    public ResponseEntity report(HttpSession session, @PathVariable("room-id") Long roomId, @RequestBody AskDto.AskReportRequest request) {
        return askService.report(session, roomId, request);
    }

}
