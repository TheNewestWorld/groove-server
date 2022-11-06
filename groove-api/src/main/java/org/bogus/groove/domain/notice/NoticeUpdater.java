package org.bogus.groove.domain.notice;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.NoticeRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeUpdater {

    private final NoticeRepository noticeRepository;

    @Transactional
    public void updateNotice(Long noticeId, String title, String content) {
        var entity = noticeRepository.findById(noticeId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND));
        entity.update(title, content);
    }
}
