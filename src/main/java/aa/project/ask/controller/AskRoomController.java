package aa.project.ask.controller;

import aa.project.ask.dto.AskDto;
import aa.project.ask.dto.AskRoomDto;
import aa.project.ask.service.AskRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room")
@Api(tags = {"방 생성"})
public class AskRoomController {
    private final AskRoomService askRoomService;


    @ApiOperation(value = "질문방 생성")
    @PostMapping
    public AskRoomDto.CreateAskRoomResponse createAskRoom(HttpSession session, @Validated @RequestBody AskRoomDto.CreateAskRoomRequest createAskRoomRequest) {
        return askRoomService.createAskRoom(session, createAskRoomRequest.getTitle());
    }

    @ApiOperation(value = "질문방 삭제")
    @DeleteMapping("/{room-id}")
    public ResponseEntity deleteAskRoom(HttpSession session, @PathVariable("room-id") Long roomId, @RequestBody String keyword) {
        return askRoomService.deleteAskRoom(session, roomId, keyword);
    }

    @ApiOperation(value = "질문방 조회")
    @GetMapping("/{room-id}")
    public List<AskDto.AskListResponse> roomAskList(@PathVariable("room-id") Long id) {
        return askRoomService.roomAskList(id);
    }
}
