package org.bogus.groove.domain.record;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.RecordRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RecordDeleter {
    private final RecordRepository recordRepository;

    @Transactional
    public void delete(long recordId) {
        var entity = recordRepository.findById(recordId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_RECORD));
        entity.setDeleted(true);
    }
}
