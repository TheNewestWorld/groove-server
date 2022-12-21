package org.bogus.groove.domain.inquiry;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.repository.InquiryAnswerRepository;
import org.bogus.groove.storage.repository.InquiryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InquiryDeleter {
    private final InquiryRepository inquiryRepository;
    private final InquiryAnswerRepository inquiryAnswerRepository;

    public void delete(Long id) {
        Long inquiryAnswerId = inquiryRepository.findById(id).get().getAnswerId();
        inquiryAnswerRepository.deleteById(inquiryAnswerId);
        inquiryRepository.deleteById(id);
    }
}
