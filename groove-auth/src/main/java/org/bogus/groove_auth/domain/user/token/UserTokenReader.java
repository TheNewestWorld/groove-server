package org.bogus.groove_auth.domain.user.token;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.UnauthorizedException;
import org.bogus.groove_auth.error.ErrorType;
import org.bogus.groove_auth.storage.UserTokenRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTokenReader {
    private final UserTokenRepository userTokenRepository;

    public UserToken readUserTokenByUserId(Long userId) {
        return readUserTokenByUserIdOrNull(userId).orElseThrow(
            () -> new UnauthorizedException(ErrorType.UNAUTHORIZED_NOT_FOUND_USER_TOKEN));
    }

    public Optional<UserToken> readUserTokenByUserIdOrNull(Long userId) {
        return userTokenRepository.findByUserId(userId).map(UserToken::new);
    }
}
