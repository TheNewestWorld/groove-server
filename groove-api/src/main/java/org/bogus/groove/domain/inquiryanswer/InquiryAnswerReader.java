package org.bogus.groove.domain.inquiryanswer;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.InquiryAnswerRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InquiryAnswerReader {
    private final InquiryAnswerRepository inquiryAnswerRepository;

    public InquiryAnswer read(Long inquiryId) {
        var entity =
            inquiryAnswerRepository.findByInquiryId(inquiryId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_INQUIRY_ANSWER));
        return new InquiryAnswer(entity);
    }
}
