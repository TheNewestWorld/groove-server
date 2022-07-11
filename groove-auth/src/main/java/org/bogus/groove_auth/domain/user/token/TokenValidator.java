package org.bogus.groove_auth.domain.user.token;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.UnauthorizedException;
import org.bogus.groove_auth.error.ErrorType;
import org.bogus.groove_auth.util.JwtUtil;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenValidator {
    private final JwtUtil jwtUtil;
    private final UserTokenReader userTokenReader;

    public void validateRefreshing(Long userId, String token) {
        UserToken refreshToken = userTokenReader.readUserTokenByUserId(userId);
        validate(token);
        validate(refreshToken.getToken());
        if (!token.equals(refreshToken.getToken())) {
            throw new UnauthorizedException(ErrorType.UNAUTHORIZED_INVALID_TOKEN);
        }
    }

    public void validate(String token) {
        try {
            jwtUtil.verify(token);
        } catch (JWTVerificationException e) {
            if (e instanceof TokenExpiredException) {
                throw new UnauthorizedException(ErrorType.UNAUTHORIZED_TOKEN_EXPIRED);
            } else {
                throw new UnauthorizedException(ErrorType.UNAUTHORIZED_INVALID_TOKEN);
            }
        }
    }
}
