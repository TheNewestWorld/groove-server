package org.bogus.groove.domain.record;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.storage.repository.RecordRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecordReader {
    private final RecordRepository recordRepository;

    public Slice<Record> read(long userId, int page, int size) {
        return recordRepository.findAllByUserId(userId, PageRequest.of(page, size))
            .map(Record::new);
    }

    public Record read(long recordId) {
        return recordRepository.findById(recordId).map(Record::new).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_RECORD));
    }
}
