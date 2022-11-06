package org.bogus.groove.fixture;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TestUserRegisterRequest {
    private final String email;
    private final String password;
    private final String nickname;

    public static TestUserRegisterRequest mock(int number) {
        TestUserFixture testUserFixture = TestUserFixture.get(number);
        return new TestUserRegisterRequest(
            testUserFixture.getEmail(),
            testUserFixture.getPassword(),
            testUserFixture.getNickname()
        );
    }
}
