package org.bogus.groove_auth.endpoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import org.bogus.groove_auth.domain.user.AuthService;
import org.bogus.groove_auth.domain.user.UserRegisterParam;
import org.bogus.groove_auth.domain.user.UserService;
import org.bogus.groove_auth.domain.user.UserType;
import org.bogus.groove_auth.domain.user.token.TokenGenerator;
import org.bogus.groove_auth.endpoint.auth.LoginRequest;
import org.bogus.groove_auth.endpoint.auth.TokenRefreshRequest;
import org.bogus.groove_auth.storage.UserEntity;
import org.bogus.groove_auth.storage.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

class AuthControllerTest extends BaseIntegrationTest {
    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenGenerator tokenGenerator;

    String email = "jig7357@naver.com";
    String password = "password";
    UserEntity userEntity;
    String accessToken;
    String refreshToken;

    @BeforeEach
    public void setup() {
        String email = "jig7357@naver.com";
        String password = "password";
        userService.register(new UserRegisterParam(email, password));
        userEntity = userRepository.findByEmailAndType(email, UserType.GROOVE).get();
        accessToken = tokenGenerator.generateAccessToken(userEntity.getId());
        refreshToken = tokenGenerator.generateRefreshToken(userEntity.getId());
    }

    @Test
    public void 로그인() throws Exception {
        var loginRequest = new LoginRequest(email, password);

        mvc.perform(
                post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(StandardCharsets.UTF_8)
                    .content(mapper.writeValueAsString(loginRequest))
            )
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                jsonPath("data.accessToken").isString(),
                jsonPath("data.refreshToken").isString()
            )
        ;
    }

    @Test
    public void 토큰_리프레쉬() throws Exception {
        var refreshRequest = new TokenRefreshRequest(refreshToken);

        mvc.perform(
                post("/api/auth/refresh")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, accessToken)
                    .content(mapper.writeValueAsString(refreshRequest))
            )
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                jsonPath("data.accessToken").isString()
            )
        ;
    }

    @Test
    public void 로그아웃_토큰_무효화() throws Exception {
        mvc.perform(
                post("/api/auth/logout")
                    .header(HttpHeaders.AUTHORIZATION, accessToken)
            )
            .andDo(print())
            .andExpect(status().isOk())
        ;

        mvc.perform(
                get("/api/users/self")
                    .header(HttpHeaders.AUTHORIZATION, accessToken)
            )
            .andDo(print())
            .andExpect(status().isUnauthorized())
        ;
    }
}