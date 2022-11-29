package org.bogus.groove;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.Password;
import org.bogus.groove.domain.user.User;
import org.bogus.groove.domain.user.UserRegisterParam;
import org.bogus.groove.domain.user.UserService;
import org.bogus.groove.domain.user.token.TokenGenerator;
import org.bogus.groove.fixture.TestUserRegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RequiredArgsConstructor
class UserControllerTest extends BaseIntegrationTest {
    private final UserService userService;
    private final TokenGenerator tokenGenerator;

    User user;
    String accessToken;

    @BeforeEach
    public void setup() {
        var userMock = TestUserRegisterRequest.mock(1);
        user = userService.register(
            new UserRegisterParam(userMock.getEmail(), new Password(userMock.getPassword()), userMock.getNickname())
        );
        accessToken = tokenGenerator.generateAccessToken(user.getId());
    }

   /*
    @Test
    public void 회원가입() throws Exception {
        var registerRequest = TestUserRegisterRequest.mock(2);

        mvc.perform(
                MockMvcRequestBuilders.post("/api/users/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(registerRequest))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
        ;
        Assertions.assertTrue(userRepository.findByEmailAndType(registerRequest.getEmail(), UserType.GROOVE).isPresent());
    }
    */

    @Test
    public void 자신의_정보를_조회한다() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/api/users/self")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, accessToken)
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("data.id").value(user.getId())
            )
        ;
    }
}
