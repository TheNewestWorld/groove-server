package org.bogus.groove.endpoint.mail;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.user.EmailAuthenticationRequester;
import org.bogus.groove.domain.user.PasswordChangeRequester;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final EmailAuthenticationRequester emailAuthenticationRequester;
    private final PasswordChangeRequester passwordChangeRequester;

    public void sendAuthenticationMail(String email) {
        emailAuthenticationRequester.request(email);
    }

    public void sendPasswordUpdateLink(String email) {
        passwordChangeRequester.request(email);
    }
}
