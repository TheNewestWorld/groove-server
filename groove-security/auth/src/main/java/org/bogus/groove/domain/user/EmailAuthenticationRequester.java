package org.bogus.groove.domain.user;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.mail.config.EmailAuthenticationEmailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EmailAuthenticationRequester {
    private final EmailAuthenticationCreator emailAuthenticationCreator;
    private final EmailAuthenticationEmailSender emailAuthenticationEmailSender;

    @Transactional
    public void request(String email) {
        var emailAuthentication = emailAuthenticationCreator.create(email);
        emailAuthenticationEmailSender.send(email, emailAuthentication.getSessionKey());
    }
}
