package org.bogus.groove.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.mail.config.PasswordChangeEmailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PasswordChangeRequester {
    private final PasswordChangeEmailSender passwordChangeEmailSender;
    private final EmailAuthenticationCreator emailAuthenticationCreator;

    @Transactional
    public void request(String email) {
        var emailAuthentication = emailAuthenticationCreator.create(email);
        passwordChangeEmailSender.send(email, emailAuthentication.getSessionKey());
    }
}
