package mapper;

import dto.RequestDto;
import entity.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    RequestDto toDTO(Request request);

    @Mapping(target = "id", ignore = true)
    Request toEntity(RequestDto requestDto);

    List<RequestDto> toDtosList(List<Request> requestList);

}
