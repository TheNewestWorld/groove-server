package org.bogus.groove.domain.inquiryanswer;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.entity.InquiryAnswerEntity;
import org.bogus.groove.storage.repository.InquiryAnswerRepository;
import org.bogus.groove.storage.repository.InquiryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InquiryAnswerCreator {
    private final InquiryAnswerRepository inquiryAnswerRepository;
    private final InquiryRepository inquiryRepository;

    @Transactional
    public InquiryAnswer create(Long refInquiryId, String title, String content) {
        var inquiryAnswer = inquiryAnswerRepository.save(new InquiryAnswerEntity(refInquiryId, title, content));
        var inquiryEntity = inquiryRepository.findById(refInquiryId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_INQUIRY));
        inquiryEntity.setAnswer(true);
        inquiryEntity.setAnswerId(inquiryAnswer.getId());
        inquiryRepository.save(inquiryEntity);
        return new InquiryAnswer(inquiryAnswer);
    }

}
