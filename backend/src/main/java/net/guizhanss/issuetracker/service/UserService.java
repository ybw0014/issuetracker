package net.guizhanss.issuetracker.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.guizhanss.issuetracker.dto.request.UserCreateRequest;
import net.guizhanss.issuetracker.dto.request.UserLoginRequest;
import net.guizhanss.issuetracker.entity.User;

public interface UserService extends IService<User> {
    User create(UserCreateRequest request);

    User login(UserLoginRequest request);

    User findByUsername(String username);
}
