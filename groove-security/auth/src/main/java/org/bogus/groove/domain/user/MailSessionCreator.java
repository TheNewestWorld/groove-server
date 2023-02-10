package org.bogus.groove.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.MailSessionEntity;
import org.bogus.groove.storage.repository.MailSessionRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSessionCreator {
    private final MailSessionRepository repository;

    public MailSession create(long userId) {
        var entity = repository.save(
            new MailSessionEntity(
                userId,
                UUID.randomUUID().toString(),
                LocalDateTime.now().plusHours(1)
            )
        );
        return new MailSession(entity);
    }
}
