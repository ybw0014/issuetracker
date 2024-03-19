package net.guizhanss.issuetracker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.guizhanss.issuetracker.dto.request.UserCreateRequest;
import net.guizhanss.issuetracker.dto.request.UserLoginRequest;
import net.guizhanss.issuetracker.entity.User;
import net.guizhanss.issuetracker.entity.enums.Role;
import net.guizhanss.issuetracker.exception.InvalidCredentialsException;
import net.guizhanss.issuetracker.exception.UserExistException;
import net.guizhanss.issuetracker.mapper.UserMapper;
import net.guizhanss.issuetracker.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(UserCreateRequest request) throws UserExistException {
        var wrapper = new LambdaQueryWrapper<User>()
            .select(User::getUsername)
            .eq(User::getUsername, request.getUsername());
        if (getOne(wrapper) != null) {
            throw new UserExistException();
        }
        User user = User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
        save(user);
        return user;
    }

    @Override
    public User login(UserLoginRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
                )
            );
        } catch (AuthenticationException ex) {
            logger.error("Invalid credentials", ex);
            // use our own exception
            throw new InvalidCredentialsException();
        }
        return getByUsername(request.getUsername());
    }

    @Override
    public User getByUsername(String username) {
        return getOne(new LambdaQueryWrapper<User>()
            .eq(User::getUsername, username));
    }
}
