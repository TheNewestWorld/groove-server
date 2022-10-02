package org.bogus.groove.domain.record;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.repository.RecordRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecordReader {
    private final RecordRepository recordRepository;

    public Slice<Record> findAll(long userId, int page, int size) {
        return recordRepository.findAllByUserId(userId, PageRequest.of(page, size))
            .map(Record::new);
    }
}
