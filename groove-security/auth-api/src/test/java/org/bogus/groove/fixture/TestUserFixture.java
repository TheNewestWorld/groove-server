package org.bogus.groove.fixture;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TestUserFixture {
    private final String email;
    private final String password;
    private final String nickname;

    public static TestUserFixture get(int number) {
        return new TestUserFixture(
            "test" + number + "@test.com",
            "test" + number,
            "닉네임" + number
        );
    }
}
