package net.guizhanss.issuetracker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.guizhanss.issuetracker.entity.Token;

public interface TokenService extends IService<Token> {
    Token findByToken(String token);

    boolean isTokenValid(Token token);
}
