package org.bogus.groove.domain.notice;

import java.time.LocalDateTime;
import lombok.Getter;
import org.bogus.groove.storage.entity.NoticeEntity;

@Getter
public class Notice {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Notice(NoticeEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }

}
