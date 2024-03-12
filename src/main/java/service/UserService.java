package service;

import dto.UserDto;

import java.util.List;


public interface UserService {


    UserDto save(UserDto userDto);

    UserDto findById(Long id);

    List<UserDto> findAll();


}
