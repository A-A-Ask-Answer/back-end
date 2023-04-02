package aa.project.ask.mapper;

import aa.project.ask.dto.AskDto;
import aa.project.ask.entity.Ask;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AskMapper {

    AskMapper INSTANCE = Mappers.getMapper(AskMapper.class);

    AskDto.AskListResponse entityListToResponse(List<Ask> asks);
}
