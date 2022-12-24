package org.bogus.groove.endpoint.notice;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.notice.Notice;

@Getter
@RequiredArgsConstructor
public class NoticeGetResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final Boolean isNew;

    NoticeGetResponse(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.createdAt = notice.getCreatedAt();
        this.isNew = notice.getCreatedAt().isAfter(LocalDateTime.now().minusDays(7));
    }

}
