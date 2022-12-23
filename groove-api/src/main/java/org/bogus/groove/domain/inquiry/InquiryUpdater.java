package org.bogus.groove.domain.inquiry;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.common.exception.UnauthorizedException;
import org.bogus.groove.storage.repository.InquiryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InquiryUpdater {
    private final InquiryRepository inquiryRepository;

    @Transactional
    public void update(Long id, Long userId, String title, String content) {
        var entity = inquiryRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_INQUIRY));
        if (userId == entity.getUserId()) {
            entity.update(title, content);
        } else {
            throw new UnauthorizedException(ErrorType.FORBIDDEN_NOT_ENOUGH_AUTHORITY);
        }
    }
}
