package org.bogus.groove;

import java.nio.charset.StandardCharsets;
import org.bogus.groove.domain.user.AuthService;
import org.bogus.groove.domain.user.UserRegisterParam;
import org.bogus.groove.domain.user.UserService;
import org.bogus.groove.domain.user.UserType;
import org.bogus.groove.domain.user.token.TokenGenerator;
import org.bogus.groove.endpoint.auth.LoginRequest;
import org.bogus.groove.endpoint.auth.TokenRefreshRequest;
import org.bogus.groove.storage.UserEntity;
import org.bogus.groove.storage.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class AuthControllerTest extends BaseIntegrationTest {
    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenGenerator tokenGenerator;

    @Autowired
    PasswordEncoder passwordEncoder;

    String email = "jig7357@naver.com";
    String password = "password";
    UserEntity userEntity;
    String accessToken;
    String refreshToken;

    @BeforeEach
    public void setup() {
        userService.register(new UserRegisterParam(email, passwordEncoder.encode(password)));
        userEntity = userRepository.findByEmailAndType(email, UserType.GROOVE).get();
        accessToken = tokenGenerator.generateAccessToken(userEntity.getId());
        refreshToken = tokenGenerator.generateRefreshToken(userEntity.getId());
    }

    @Test
    public void 로그인() throws Exception {
        var loginRequest = new LoginRequest(email, password);

        mvc.perform(
                MockMvcRequestBuilders.post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(StandardCharsets.UTF_8)
                    .content(mapper.writeValueAsString(loginRequest))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("data.accessToken").isString(),
                MockMvcResultMatchers.jsonPath("data.refreshToken").isString()
            )
        ;
    }

    @Test
    public void 토큰_리프레쉬() throws Exception {
        var refreshRequest = new TokenRefreshRequest(refreshToken);

        mvc.perform(
                MockMvcRequestBuilders.post("/api/auth/refresh")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, accessToken)
                    .content(mapper.writeValueAsString(refreshRequest))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("data.accessToken").isString()
            )
        ;
    }

    @Test
    public void 로그아웃_토큰_무효화() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/api/auth/logout")
                    .header(HttpHeaders.AUTHORIZATION, accessToken)
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        mvc.perform(
                MockMvcRequestBuilders.get("/api/users/self")
                    .header(HttpHeaders.AUTHORIZATION, accessToken)
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
        ;
    }
}