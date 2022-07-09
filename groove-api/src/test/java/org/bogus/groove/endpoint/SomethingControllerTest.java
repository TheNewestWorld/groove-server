package org.bogus.groove.endpoint;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.bogus.groove.domain.something.Something;
import org.bogus.groove.domain.something.SomethingService;
import org.bogus.groove.domain.user.UserInfo;
import org.bogus.groove.domain.user.UserType;
import org.bogus.groove.endpoint.something.SomethingUpdateRequest;
import org.bogus.groove_auth.endpoint.auth.AuthController;
import org.bogus.groove_auth.endpoint.auth.LoginRequest;
import org.bogus.groove_auth.endpoint.auth.RegisterRequest;
import org.bogus.groove_auth.endpoint.user.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

class SomethingControllerTest extends BaseIntegrationTest {
    @Autowired
    SomethingService somethingService;

    @Autowired
    AuthController authController;

    @Autowired
    UserController userController;

    UserInfo userInfo;
    Something something;
    String accessToken;

    @BeforeEach
    public void setup() {
        var registerResult = userController.register(new RegisterRequest("jig7357@naver.com")).getData();
        userInfo = new UserInfo(
            registerResult.getId(),
            registerResult.getEmail(),
            UserType.valueOf(registerResult.getType().name()),
            registerResult.getAuthorities()
        );
        something = somethingService.create("TEST", 11);
        accessToken = authController.login(new LoginRequest(userInfo.getEmail())).getData().getAccessToken();
    }

    @Test
    public void 무언가를_조회한다() throws Exception {
        mvc.perform(
                get(String.format("/api/somethings/%d", something.getId()))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                jsonPath("data.id").isNumber(),
                jsonPath("data.name").isString(),
                jsonPath("data.age").isNumber()
            )
        ;
    }

    @Test
    public void 무언가의_나이_업데이트_실패_토큰없음() throws Exception {
        var request = new SomethingUpdateRequest(300);

        mvc.perform(
                put(String.format("/api/somethings/%d", something.getId()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(request))
            )
            .andDo(print())
            .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    public void 무언가의_나이_업데이트() throws Exception {
        var request = new SomethingUpdateRequest(300);

        mvc.perform(
                put(String.format("/api/somethings/%d", something.getId()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, accessToken)
                    .content(mapper.writeValueAsString(request))
            )
            .andDo(print())
            .andExpect(status().isOk())
        ;
    }

    @Test
    public void 무언가_삭제_실패_권한부족() throws Exception {
        mvc.perform(
                delete(String.format("/api/somethings/%d", something.getId()))
                    .header(HttpHeaders.AUTHORIZATION, accessToken)
            )
            .andDo(print())
            .andExpect(status().isForbidden())
        ;
    }
}