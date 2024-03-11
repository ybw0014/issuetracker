package net.guizhanss.issuetracker.controller;

import lombok.RequiredArgsConstructor;
import net.guizhanss.issuetracker.dto.request.UserCreateRequest;
import net.guizhanss.issuetracker.dto.request.UserLoginRequest;
import net.guizhanss.issuetracker.dto.response.UserCreateResponse;
import net.guizhanss.issuetracker.dto.response.UserLoginResponse;
import net.guizhanss.issuetracker.entity.Response;
import net.guizhanss.issuetracker.entity.Token;
import net.guizhanss.issuetracker.entity.User;
import net.guizhanss.issuetracker.service.JwtService;
import net.guizhanss.issuetracker.service.TokenService;
import net.guizhanss.issuetracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Response<UserCreateResponse>> register(@RequestBody UserCreateRequest request) {
        User user = userService.create(request);
        return new ResponseEntity<>(Response.success("Success", new UserCreateResponse(user.getId())), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Response<UserLoginResponse>> login(@RequestBody UserLoginRequest request) {
        User user = userService.login(request);
        String jwt = jwtService.generateToken(user);
        Token token = Token.builder()
            .token(jwt)
            .userId(user.getId())
            .createdAt(new Timestamp(jwtService.extractCreatedAt(jwt)))
            .expiresAt(new Timestamp(jwtService.extractExpiresAt(jwt)))
            .build();
        tokenService.save(token);
        return new ResponseEntity<>(Response.success("Success", new UserLoginResponse(jwt)), HttpStatus.OK);
    }
}
