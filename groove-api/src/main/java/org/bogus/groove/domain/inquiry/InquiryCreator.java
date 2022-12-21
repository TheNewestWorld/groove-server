package org.bogus.groove.domain.inquiry;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.InquiryEntity;
import org.bogus.groove.storage.repository.InquiryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InquiryCreator {
    private final InquiryRepository inquiryRepository;

    public Inquiry create(Long userId, String title, String content) {
        var entity = inquiryRepository.save(new InquiryEntity(userId, title, content));
        return new Inquiry(entity);
    }
}
