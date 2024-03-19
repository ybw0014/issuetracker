package net.guizhanss.issuetracker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.guizhanss.issuetracker.entity.RefreshToken;
import net.guizhanss.issuetracker.entity.User;

public interface RefreshTokenService extends IService<RefreshToken> {
    RefreshToken createToken(User user);

    RefreshToken findByToken(String token);

    boolean isTokenValid(RefreshToken token);
}
