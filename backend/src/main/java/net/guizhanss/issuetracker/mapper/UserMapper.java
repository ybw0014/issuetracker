package net.guizhanss.issuetracker.mapper;

import lombok.experimental.UtilityClass;
import net.guizhanss.issuetracker.dto.UserDto;
import net.guizhanss.issuetracker.entity.User;

import java.time.LocalDateTime;

@UtilityClass
public final class UserMapper {
    public static UserDto mapToDto(User user) {
        return new UserDto(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword()
        );
    }

    public static User mapToEntity(UserDto userDto) {
        return new User(
            userDto.getId(),
            userDto.getUsername(),
            userDto.getEmail(),
            userDto.getPassword(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }
}
