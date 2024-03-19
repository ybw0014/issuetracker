package net.guizhanss.issuetracker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.guizhanss.issuetracker.entity.Token;
import net.guizhanss.issuetracker.entity.User;

public interface TokenService extends IService<Token> {
    Token createToken(User user);

    Token findByToken(String token);

    boolean isTokenValid(Token token);
}
