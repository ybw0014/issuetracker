package net.guizhanss.issuetracker.service;

import io.jsonwebtoken.Jwts;
import net.guizhanss.issuetracker.config.JwtConfig;
import net.guizhanss.issuetracker.dto.request.UserCreateRequest;
import net.guizhanss.issuetracker.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.codec.Hex;

import java.util.Date;

@SpringBootTest
class JwtServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private JwtConfig jwtConfig;

    @Test
    void testConfig() {
        // make sure the secret environment variable is set
        Assertions.assertNotEquals("${JWT_SECRET}", jwtConfig.getSecret());

        // make sure the expiration time is set
        Assertions.assertTrue(jwtConfig.getExpiration() > 0);
        Assertions.assertTrue(jwtConfig.getRefreshExpiration() > 0);
    }

    @Test
    void testGenerateToken() {
        final User user = userService.create(
            new UserCreateRequest("test", "example@domain.com", "114.514")
        );
        final String jwt = jwtService.generateToken(user);
        Assertions.assertNotNull(jwt);
        System.out.println(jwt);

        Assertions.assertEquals("test", jwtService.extractUsername(jwt));
        Assertions.assertTrue(jwtService.isTokenValid(jwt, user));
    }
}
