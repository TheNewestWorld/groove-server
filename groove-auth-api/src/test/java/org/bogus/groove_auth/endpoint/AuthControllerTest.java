package org.bogus.groove_auth.endpoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.bogus.groove.common.UserInfo;
import org.bogus.groove_auth.domain.user.AuthService;
import org.bogus.groove_auth.domain.user.UserService;
import org.bogus.groove_auth.endpoint.auth.LoginRequest;
import org.bogus.groove_auth.endpoint.auth.TokenRefreshRequest;
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

    private UserInfo userInfo;

    @BeforeEach
    public void setup() {
        userInfo = userService.register("jig7357@naver.com");
    }

    @Test
    public void 로그인() throws Exception {
        var loginRequest = new LoginRequest(userInfo.getEmail());

        mvc.perform(
                post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
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
        var loginResult = authService.login(userInfo.getEmail());
        var refreshRequest = new TokenRefreshRequest(loginResult.getRefreshToken());

        mvc.perform(
                post("/api/auth/refresh")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, loginResult.getAccessToken())
                    .content(mapper.writeValueAsString(refreshRequest))
            )
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                jsonPath("data.accessToken").isString()
            )
        ;
    }
}