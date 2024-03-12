package service.impl;

import dto.UserDto;
import entity.User;
import lombok.AllArgsConstructor;
import mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;
import service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto save(UserDto userDto) {
        final User user =
                userRepository.saveAndFlush(userMapper.toEntity(userDto));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDTO(userRepository.findById(id).orElse(null));
    }

    @Override
    public List<UserDto> findAll() {
        return userMapper.toDtosList(userRepository.findAll());
    }


}
