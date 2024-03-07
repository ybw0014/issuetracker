package net.guizhanss.issuetracker.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import net.guizhanss.issuetracker.entity.Token;
import net.guizhanss.issuetracker.mapper.TokenMapper;
import net.guizhanss.issuetracker.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {

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
