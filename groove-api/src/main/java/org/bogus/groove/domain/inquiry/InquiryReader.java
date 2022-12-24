package org.bogus.groove.domain.inquiry;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.InquiryAnswerRepository;
import org.bogus.groove.storage.repository.InquiryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InquiryReader {

    private final InquiryRepository inquiryRepository;
    private final InquiryAnswerRepository inquiryAnswerRepository;

    public Inquiry read(Long id) {
        var entity = inquiryRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_INQUIRY));
        return new Inquiry(entity);
    }

    public Slice<Inquiry> readAll(Long userId, Pageable pageable) {
        return inquiryRepository.findAllByUserIdOrderByCreatedAtDesc(userId, pageable).map(Inquiry::new);
    }
}
