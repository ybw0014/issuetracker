package net.guizhanss.issuetracker.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import net.guizhanss.issuetracker.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
            .build();
        userMapper.insert(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals("test", user.getUsername());
        Assertions.assertEquals("114.514", user.getPassword());
    }
}
