package net.guizhanss.issuetracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String username;
    private String email;
    private String password;
}
