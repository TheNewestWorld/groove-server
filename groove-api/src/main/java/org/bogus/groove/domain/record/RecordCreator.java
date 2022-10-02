package org.bogus.groove.domain.record;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.RecordEntity;
import org.bogus.groove.storage.repository.RecordRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecordCreator {
    private final RecordRepository repository;

    public void create(long userId, long attachmentId) {
        repository.save(new RecordEntity(userId, attachmentId));
    }
}
