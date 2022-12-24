package org.bogus.groove.domain.notification;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bogus.groove.common.enumeration.NotificationType;
import org.bogus.groove.storage.entity.NotificationEntity;

@Getter
@AllArgsConstructor
public class Notification {
    private Long id;
    private LocalDateTime createdAt;
    private String content;
    private NotificationType notificationType;
    private boolean readFlag;
    private boolean deleteFlag;
    private String linkUrl;
    private Long userId;

    public Notification(NotificationEntity entity) {
        this.id = entity.getId();
        this.createdAt = entity.getCreatedAt();
        this.content = entity.getContent();
        this.notificationType = entity.getNotificationType();
        this.readFlag = entity.isReadFlag();
        this.deleteFlag = entity.isDeleteFlag();
        this.linkUrl = entity.getLinkUrl();
        this.userId = entity.getUserId();
    }
}
