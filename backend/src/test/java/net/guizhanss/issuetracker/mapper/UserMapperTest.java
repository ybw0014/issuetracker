package net.guizhanss.issuetracker.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import net.guizhanss.issuetracker.entity.User;
import net.guizhanss.issuetracker.entity.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

@MybatisPlusTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testCreate() {
        User user = User.builder()
            .username("test")
            .email("example@domain.com")
            .password("114.514")
            .role(Role.USER)
            .createdAt(new Timestamp(System.currentTimeMillis()))
            .updatedAt(new Timestamp(System.currentTimeMillis()))
            .build();
        userMapper.insert(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals("test", user.getUsername());
        Assertions.assertEquals("114.514", user.getPassword());
    }
}
