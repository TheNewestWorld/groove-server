package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailAuthenticator {

    private final EmailAuthenticationUpdater emailAuthenticationUpdater;
    private final UserUpdater userUpdater;

    public void authenticate(String sessionKey) {
        var emailAuthentication = emailAuthenticationUpdater.update(sessionKey, true, LocalDateTime.now());

        userUpdater.update(
            emailAuthentication.getUserId(),
            emailAuthentication.isAuthenticated(),
            emailAuthentication.getAuthenticatedAt()
        );
    }
}
