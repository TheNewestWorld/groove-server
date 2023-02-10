package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.user.token.TokenGenerator;
import org.bogus.groove.domain.user.token.TokenValidator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthService {
    private final TokenGenerator tokenGenerator;
    private final TokenValidator tokenValidator;
    private final MailSessionReader mailSessionReader;
    private final UserUpdater userUpdater;

    public String refresh(long userId, String refreshToken) {
        tokenValidator.validateRefreshable(userId, refreshToken);
        return tokenGenerator.generateAccessToken(userId);
    }

    public void logout(String token) {
        tokenValidator.invalidate(token);
    }

    public void authenticateEmail(String sessionKey) {
        var mailSession = mailSessionReader.read(sessionKey);

//        if (emailAuthentication.getExpiredAt().isBefore(LocalDateTime.now())) {
//            throw new NotFoundException(ErrorType.AUTHENTICATION_SESSION_EXPIRED);
//        }

        userUpdater.update(
            mailSession.getUserId(),
            true,
            LocalDateTime.now()
        );
    }
}
