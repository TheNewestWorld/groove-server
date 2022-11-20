package org.bogus.groove.domain.notice;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.NoticeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoticeReader {

    private final NoticeRepository noticeRepository;

    public Slice<Notice> readAll(Pageable pageable) {
        var slice = noticeRepository.findAllByOrderByCreatedAtDesc(pageable);

        return slice.map(Notice::new);
    }

    public Notice read(Long noticeId) {
        return noticeRepository.findById(noticeId)
            .map(Notice::new)
            .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND));
    }


}
