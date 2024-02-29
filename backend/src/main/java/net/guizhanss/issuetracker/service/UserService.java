package net.guizhanss.issuetracker.service;

import net.guizhanss.issuetracker.dto.UserDto;

public interface UserService {
    UserDto create(UserDto userDto);
    UserDto getById(Long id);
}
