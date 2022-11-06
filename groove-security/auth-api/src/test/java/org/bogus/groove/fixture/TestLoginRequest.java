package org.bogus.groove.fixture;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TestLoginRequest {
    private final String email;
    private final String password;

    public static TestLoginRequest mock(int number) {
        TestUserFixture testUserFixture = TestUserFixture.get(number);
        return new TestLoginRequest(
            testUserFixture.getEmail(),
            testUserFixture.getPassword()
        );
    }
}
