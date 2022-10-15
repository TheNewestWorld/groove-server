package org.bogus.groove.domain.record;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.RecordRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecordReader {
    private final RecordRepository recordRepository;

    public Slice<Record> read(long userId, int page, int size) {
        return recordRepository.findAllByUserIdAndDeletedIsFalse(userId, PageRequest.of(page, size))
            .map(Record::new);
    }

    public Record read(long recordId) {
        var entity = recordRepository.findById(recordId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_RECORD));
        if (entity.isDeleted()) {
            throw new NotFoundException(ErrorType.NOT_FOUND_RECORD);
        }

        return new Record(entity);
    }
}
