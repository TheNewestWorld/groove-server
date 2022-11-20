package org.bogus.groove;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.Password;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.common.exception.UnauthorizedException;
import org.bogus.groove.domain.user.User;
import org.bogus.groove.domain.user.UserReader;
import org.bogus.groove.domain.user.UserRegister;
import org.bogus.groove.domain.user.UserRegisterParam;
import org.bogus.groove.domain.user.token.TokenGenerator;
import org.bogus.groove.domain.user.token.TokenValidator;
import org.bogus.groove.fixture.TestUserRegisterRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RequiredArgsConstructor
class UserControllerTest extends BaseIntegrationTest {
    private final UserRegister userRegister;
    private final UserReader userReader;
    private final TokenGenerator tokenGenerator;
    private final TokenValidator tokenValidator;

    User user;
    String accessToken;

    @BeforeEach
    public void setup() {
        var userMock = TestUserRegisterRequest.mock(1);
        user = userRegister.register(
            new UserRegisterParam(
                userMock.getEmail(),
                new Password(userMock.getPassword()),
                userMock.getNickname()
            )
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

    @Test
    public void 탈퇴한_유저는_조회되지_않는다() throws Exception {
        mvc.perform(
            MockMvcRequestBuilders.delete("/api/users/self")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, accessToken)
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThrows(NotFoundException.class, () -> userReader.read(user.getId()));
        Assertions.assertThrows(NotFoundException.class, () -> userReader.read(user.getEmail(), user.getType()));
        Assertions.assertThrows(UnauthorizedException.class, () -> tokenValidator.validate(accessToken));
    }
}
