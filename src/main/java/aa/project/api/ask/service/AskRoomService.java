package aa.project.api.ask.service;

import aa.project.api.ask.dto.AskRoomDto;
import aa.project.api.ask.repository.AskRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class AskRoomService {
    private final AskRoomRepository askRoomRepository;

    @Transactional
    public AskRoomDto.Response create(AskRoomDto.Request request, HttpSession session) {
        session.setAttribute("ID", "아이디값");
        return null;
    }
}
