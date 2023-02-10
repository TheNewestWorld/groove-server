package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.mail.config.EmailType;
import org.bogus.groove.mail.config.GoogleMailSender;
import org.bogus.groove.storage.entity.MailSessionEntity;
import org.bogus.groove.storage.repository.MailSessionRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSessionCreator {
    private final MailSessionRepository repository;
    private final GoogleMailSender googleMailSender;

    public MailSession create(long userId, String email, EmailType type) {
        var entity = repository.save(
            new MailSessionEntity(
                userId,
                UUID.randomUUID().toString(),
                LocalDateTime.now().plusHours(1)
            )
        );

        googleMailSender.sendMessage(email, entity.getSessionKey(), type);
        return new MailSession(entity);
    }
}
