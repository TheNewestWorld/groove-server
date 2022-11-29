package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.mail.config.EmailType;
import org.bogus.groove.mail.config.GoogleMailSender;
import org.bogus.groove.storage.entity.EmailAuthenticationEntity;
import org.bogus.groove.storage.repository.EmailAuthenticationRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailAuthenticationCreator {
    private final EmailAuthenticationRepository repository;
    private final GoogleMailSender googleMailSender;

    public EmailAuthentication create(long userId, String email, EmailType type) {
        var entity = repository.save(
            new EmailAuthenticationEntity(
                userId,
                UUID.randomUUID().toString(),
                LocalDateTime.now().plusHours(1)
            )
        );

        googleMailSender.sendMessage(email, entity.getSessionKey(), type);
        return new EmailAuthentication(entity);
    }
}
