package net.guizhanss.issuetracker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import net.guizhanss.issuetracker.entity.Token;
import net.guizhanss.issuetracker.entity.User;
import net.guizhanss.issuetracker.mapper.TokenMapper;
import net.guizhanss.issuetracker.service.JwtService;
import net.guizhanss.issuetracker.service.TokenService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {
    private final JwtService jwtService;

    @Override
    public Token createToken(User user) {
        String jwt = jwtService.generateToken(user);

        Token token = Token.builder()
            .token(jwt)
            .userId(user.getId())
            .createdAt(new Timestamp(jwtService.extractCreatedAt(jwt)))
            .expiresAt(new Timestamp(jwtService.extractExpiresAt(jwt)))
            .build();
        save(token);
        return token;
    }

    @Override
    public Token findByToken(String token) {
        return getOne(new LambdaQueryWrapper<Token>()
            .eq(Token::getToken, token));
    }

    @Override
    public boolean isTokenValid(Token token) {
        if (token == null) {
            return false;
        }
        if (token.getExpiresAt().before(new Date())) {
            return false;
        }
        return !token.isRevoked();
    }
}
