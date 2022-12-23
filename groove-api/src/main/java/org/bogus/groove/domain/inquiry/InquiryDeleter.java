package org.bogus.groove.domain.inquiry;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.repository.InquiryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InquiryDeleter {
    private final InquiryRepository inquiryRepository;

    public void delete(Long id) {
        inquiryRepository.deleteById(id);
    }
}
