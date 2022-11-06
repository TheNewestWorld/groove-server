package org.bogus.groove.domain.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeCreator noticeCreator;
    private final NoticeReader noticeReader;
    private final NoticeUpdater noticeUpdater;
    private final NoticeDeleter noticeDeleter;

    public void createNotice(String title, String content) {
        noticeCreator.createNotice(title, content);
    }

    public void updateNotice(Long noticeId, String title, String content) {
        noticeUpdater.updateNotice(noticeId, title, content);
    }

    public void deleteNotice(Long noticeId) {
        noticeDeleter.deleteNotice(noticeId);
    }

    public Slice<Notice> getNoticeList(int page, int size) {
        return noticeReader.readAll(PageRequest.of(page, size));
    }

    public Notice getNotice(Long noticeId) {
        return noticeReader.read(noticeId);
    }
}
