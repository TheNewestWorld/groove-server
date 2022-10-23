package org.bogus.groove;

import org.bogus.groove.domain.user.AuthService;
import org.bogus.groove.domain.user.UserRegisterParam;
import org.bogus.groove.domain.user.UserService;
import org.bogus.groove.domain.user.UserType;
import org.bogus.groove.domain.user.token.TokenGenerator;
import org.bogus.groove.endpoint.user.UserRegisterRequest;
import org.bogus.groove.storage.UserEntity;
import org.bogus.groove.storage.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class UserControllerTest extends BaseIntegrationTest {
    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenGenerator tokenGenerator;

    UserEntity userEntity;
    String accessToken;

    @BeforeEach
    public void setup() {
        String email = "jig7357@naver.com";
        String password = "password";
        String nickname = "nickname";
        userService.register(new UserRegisterParam(email, password, nickname));
        userEntity = userRepository.findByEmailAndType(email, UserType.GROOVE).get();
        accessToken = tokenGenerator.generateAccessToken(userEntity.getId());
    }

    @Test
    public void 회원가입() throws Exception {
        String email = "jig7357@google.com";
        String password = "password";
        String nickname = "nickname2";
        var registerRequest = new UserRegisterRequest(email, password, nickname);

        mvc.perform(
                MockMvcRequestBuilders.post("/api/users/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(registerRequest))
            )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
        ;
        Assertions.assertTrue(userRepository.findByEmailAndType(email, UserType.GROOVE).isPresent());
    }

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
                MockMvcResultMatchers.jsonPath("data.id").value(userEntity.getId())
            )
        ;
    }
}
