package net.guizhanss.issuetracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.guizhanss.issuetracker.dto.request.RefreshTokenRequest;
import net.guizhanss.issuetracker.dto.request.UserCreateRequest;
import net.guizhanss.issuetracker.dto.request.UserLoginRequest;
import net.guizhanss.issuetracker.dto.response.RefreshTokenResponse;
import net.guizhanss.issuetracker.dto.response.UserCreateResponse;
import net.guizhanss.issuetracker.dto.response.UserLoginResponse;
import net.guizhanss.issuetracker.entity.RefreshToken;
import net.guizhanss.issuetracker.entity.Response;
import net.guizhanss.issuetracker.entity.Token;
import net.guizhanss.issuetracker.entity.User;
import net.guizhanss.issuetracker.service.RefreshTokenService;
import net.guizhanss.issuetracker.service.TokenService;
import net.guizhanss.issuetracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<Response<UserCreateResponse>> register(@Valid @RequestBody UserCreateRequest request) {
        User user = userService.create(request);
        return new ResponseEntity<>(Response.success("Success", new UserCreateResponse(user.getId())), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Response<UserLoginResponse>> login(@Valid @RequestBody UserLoginRequest request) {
        // get logged in User
        User user = userService.login(request);

        // generate token
        Token accessToken = tokenService.createToken(user);

        // generate refersh token
        RefreshToken refreshToken = refreshTokenService.createToken(user);

        UserLoginResponse response = UserLoginResponse.builder()
            .id(user.getId())
            .username(user.getUsername())
            .tokenType(accessToken.getType().getValue())
            .accessToken(accessToken.getToken())
            .expiresAt(accessToken.getExpiresAt().toLocalDateTime().toString())
            .refreshToken(refreshToken.getToken())
            .build();
        return new ResponseEntity<>(Response.success(response), HttpStatus.OK);
    }

//    @PostMapping("/refresh-token")
//    public ResponseEntity<Response<RefreshTokenResponse>> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
//        return new ResponseEntity<>(Response.success(response), HttpStatus.OK);
//    }
}
