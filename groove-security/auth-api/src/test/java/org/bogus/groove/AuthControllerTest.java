package org.bogus.groove;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.Password;
import org.bogus.groove.common.exception.UnauthorizedException;
import org.bogus.groove.domain.user.User;
import org.bogus.groove.domain.user.UserRegister;
import org.bogus.groove.domain.user.UserRegisterParam;
import org.bogus.groove.domain.user.token.TokenGenerator;
import org.bogus.groove.domain.user.token.TokenValidator;
import org.bogus.groove.endpoint.auth.TokenRefreshRequest;
import org.bogus.groove.fixture.TestLoginRequest;
import org.bogus.groove.fixture.TestUserRegisterRequest;
import org.bogus.groove.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RequiredArgsConstructor
class AuthControllerTest extends BaseIntegrationTest {
    private final UserRegister userRegister;
    private final TokenGenerator tokenGenerator;
    private final TokenValidator tokenValidator;
    private final JwtUtil jwtUtil;

    User user;
    String accessToken;
    String refreshToken;

    @BeforeEach
    public void setup() {
        var userMock = TestUserRegisterRequest.mock(1);
        user = userRegister.register(
            new UserRegisterParam(userMock.getEmail(), new Password(userMock.getPassword()), userMock.getNickname())
        );
        accessToken = tokenGenerator.generateAccessToken(user.getId());
        refreshToken = tokenGenerator.generateRefreshToken(user.getId());
    }

    @Test
    public void INACTIVE_유저_로그인() throws Exception {
        var loginRequest = TestLoginRequest.mock(1);

        mvc.perform(
                MockMvcRequestBuilders.post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding(StandardCharsets.UTF_8)
                    .content(mapper.writeValueAsString(loginRequest))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isForbidden())
        ;
    }

//    TODO emailAuthenticationCreator 분리되면 다시 추가
//    @Test
//    public void 유저_로그인() throws Exception {
//        var loginRequest = TestLoginRequest.mock(1);
//
//        mvc.perform(
//                MockMvcRequestBuilders.post("/api/auth/login")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .characterEncoding(StandardCharsets.UTF_8)
//                    .content(mapper.writeValueAsString(loginRequest))
//            )
//            .andDo(MockMvcResultHandlers.print())
//            .andExpectAll(
//                MockMvcResultMatchers.status().isOk(),
//                MockMvcResultMatchers.jsonPath("data.accessToken").isString(),
//                MockMvcResultMatchers.jsonPath("data.refreshToken").isString()
//            )
//        ;
//    }

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
    public void 만료_토큰_리프레쉬() throws Exception {
        var refreshRequest = new TokenRefreshRequest(refreshToken);
        var expiredAccessToken = jwtUtil.generateToken(user.getId(), LocalDateTime.now().minusSeconds(1), "access").getToken();

        mvc.perform(
                MockMvcRequestBuilders.post("/api/auth/refresh")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, expiredAccessToken)
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
    public void 리프레쉬_토큰_만료_시_예외를_던진다() throws Exception {
        var expiredRefreshToken = jwtUtil.generateToken(user.getId(), LocalDateTime.now().minusSeconds(1), "refresh").getToken();
        var refreshRequest = new TokenRefreshRequest(expiredRefreshToken);

        mvc.perform(
                MockMvcRequestBuilders.post("/api/auth/refresh")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, accessToken)
                    .content(mapper.writeValueAsString(refreshRequest))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isUnauthorized())
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

        Assertions.assertThrows(UnauthorizedException.class, () -> tokenValidator.validate(accessToken));
    }
}