package org.bogus.groove_auth.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.UserInfo;
import org.bogus.groove.common.enumeration.UserType;
import org.bogus.groove_auth.domain.user.authority.UserAuthorityReader;
import org.bogus.groove_auth.error.AppException;
import org.bogus.groove_auth.error.ErrorType;
import org.bogus.groove_auth.util.JwtUtil;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoFinder {
    private final UserReader userReader;
    private final UserAuthorityReader userAuthorityReader;
    private final JwtUtil jwtUtil;

    public UserInfo find(String token) {
        var userId = jwtUtil.getUserIdByToken(token);
        return find(userId);
    }

    public UserInfo find(Long userId) {
        var user = userReader.read(userId);
        var authorities = userAuthorityReader.readAll(userId);

        return new UserInfo(user.getId(), user.getEmail(), user.getType(), authorities);
    }

    public UserInfo find(String email, UserType userType) {
        var user = userReader.readOrNull(email, userType).orElseThrow(() -> new AppException(ErrorType.NOT_FOUND_USER));

        var authorities = userAuthorityReader.readAll(user.getId());

        return new UserInfo(user.getId(), user.getEmail(), user.getType(), authorities);
    }
}
