package mapper;

import dto.UserDto;
import entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDTO(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto userDto);

    List<UserDto> toDtosList(List<User> userList);

}
