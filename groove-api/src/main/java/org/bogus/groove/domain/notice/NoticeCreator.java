package org.bogus.groove.domain.notice;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.NoticeEntity;
import org.bogus.groove.storage.repository.NoticeRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeCreator {

    private final NoticeRepository noticeRepository;

    public Notice createNotice(String title, String content) {
        var entity = noticeRepository.save(new NoticeEntity(title, content));
        return new Notice(entity);
    }
}
