package net.guizhanss.issuetracker.service.impl;

import lombok.AllArgsConstructor;
import net.guizhanss.issuetracker.dto.UserDto;
import net.guizhanss.issuetracker.entity.User;
import net.guizhanss.issuetracker.exception.UserNotFoundException;
import net.guizhanss.issuetracker.mapper.UserMapper;
import net.guizhanss.issuetracker.repository.UserRepository;
import net.guizhanss.issuetracker.service.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public final class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public UserDto create(UserDto userDto) {
        User user = UserMapper.mapToEntity(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToDto(savedUser);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return UserMapper.mapToDto(user);
    }
}
