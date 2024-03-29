package net.guizhanss.issuetracker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import net.guizhanss.issuetracker.entity.RefreshToken;
import net.guizhanss.issuetracker.entity.User;
import net.guizhanss.issuetracker.mapper.RefreshTokenMapper;
import net.guizhanss.issuetracker.service.JwtService;
import net.guizhanss.issuetracker.service.RefreshTokenService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl extends ServiceImpl<RefreshTokenMapper, RefreshToken> implements RefreshTokenService {
    private final JwtService jwtService;

    @Override
    public RefreshToken createToken(User user) {
        String jwt = jwtService.generateToken(user);

        RefreshToken token = RefreshToken.builder()
            .token(jwt)
            .userId(user.getId())
            .createdAt(new Timestamp(jwtService.extractCreatedAt(jwt)))
            .expiresAt(new Timestamp(jwtService.extractExpiresAt(jwt)))
            .build();
        save(token);
        return token;
    }

    @Override
    public RefreshToken findByToken(String token) {
        return getOne(new LambdaQueryWrapper<RefreshToken>()
            .eq(RefreshToken::getToken, token));
    }

    @Override
    public boolean isTokenValid(RefreshToken token) {
        if (token == null) {
            return false;
        }
        return !token.getExpiresAt().before(new Date());
    }
}
