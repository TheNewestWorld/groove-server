package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.domain.user.authority.Authority;
import org.bogus.groove.domain.user.authority.UserAuthorityUpdater;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailAuthenticator {

    private final EmailAuthenticationReader emailAuthenticationReader;
    private final EmailAuthenticationUpdater emailAuthenticationUpdater;
    private final UserAuthorityUpdater userAuthorityUpdater;
    private final UserUpdater userUpdater;

    @Transactional
    public void authenticate(String sessionKey) {
        var emailAuthentication = emailAuthenticationReader.read(sessionKey);

        if (emailAuthentication.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new NotFoundException(ErrorType.AUTHENTICATION_SESSION_EXPIRED);
        }

        var authenticated = emailAuthenticationUpdater.update(sessionKey, true, LocalDateTime.now());

        userUpdater.update(
            authenticated.getUserId(),
            authenticated.isVerified(),
            authenticated.getVerifiedAt()
        );

        userAuthorityUpdater.update(authenticated.getId(), List.of(Authority.USER));
    }
}