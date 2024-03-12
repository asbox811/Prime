package service;

import dto.UserDto;

import java.util.List;


public interface RequestService {


    void redistributeRequests(UserDto offlineUser, List<UserDto> onlineUsers);


}
