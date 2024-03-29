package net.guizhanss.issuetracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserLoginResponse {
    private Long id;
    private String username;
    private String tokenType;
    private String accessToken;
    private String expiresAt;
    private String refreshToken;
}
