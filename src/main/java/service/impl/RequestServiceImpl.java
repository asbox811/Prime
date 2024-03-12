package service.impl;

import dto.UserDto;
import entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;
import service.RequestService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {


    private final UserRepository userRepository;


    @Override
    @Transactional
    public void redistributeRequests(UserDto offlineUserDto, List<UserDto> onlineUsersDto) {
        User offlineUser = userRepository.findById(offlineUserDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Offline user not found"));

        List<User> onlineUsers = onlineUsersDto.stream()
                .map(userDto -> userRepository.findById(userDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Online user not found")))
                .collect(Collectors.toList());

        offlineUser.getRequests().forEach(request -> {
            int totalOnlineRequests = onlineUsers.stream().mapToInt(User::getTotalRequests).sum();

            User selectedUser = onlineUsers.stream()
                    .filter(u -> Math.random() * totalOnlineRequests <= u.getTotalRequests())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No user selected for redistribution"));

            selectedUser.addRequest(request);
            userRepository.save(selectedUser);
        });
    }
}
