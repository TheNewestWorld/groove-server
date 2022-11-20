package org.bogus.groove.domain.notice;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.repository.NoticeRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeDeleter {

    private final NoticeRepository noticeRepository;

    public void deleteNotice(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }
}
