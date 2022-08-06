package org.bogus.groove_auth.endpoint;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.bogus.groove_auth.domain.user.AuthService;
import org.bogus.groove_auth.domain.user.UserInfo;
import org.bogus.groove_auth.domain.user.UserService;
import org.bogus.groove_auth.domain.user.UserType;
import org.bogus.groove_auth.endpoint.auth.RegisterRequest;
import org.bogus.groove_auth.storage.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

class UserControllerTest extends BaseIntegrationTest {
    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    UserInfo userInfo;
    String accessToken;

    @BeforeEach
    public void setup() {
        userInfo = userService.register("jig7357@naver.com");
        accessToken = authService.login(userInfo.getEmail()).getAccessToken();
    }

    @Test
    public void 회원가입() throws Exception {
        String email = "jig7357@google.com";
        var registerRequest = new RegisterRequest(email);

        mvc.perform(
                post("/api/users/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(registerRequest))
            )
            .andDo(print())
            .andExpect(status().isOk())
        ;
        assertTrue(userRepository.findByEmailAndType(email, UserType.DEFAULT).isPresent());
    }

    @Test
    public void 자신의_정보를_조회한다() throws Exception {
        mvc.perform(
                get("/api/users/self")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, accessToken)
            )
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                jsonPath("data.id").value(userInfo.getId())
            )
        ;
    }
}
