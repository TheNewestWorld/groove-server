package org.bogus.groove.domain.notification;

import java.time.LocalDateTime;
import lombok.Getter;
import org.bogus.groove.common.enumeration.NotificationType;
import org.bogus.groove.storage.entity.NotificationEntity;

@Getter
public class Notification {
    private Long id;
    private LocalDateTime createdAt;
    private String content;
    private NotificationType notificationType;
    private Long targetId;
    private Long userId;

    public Notification(NotificationEntity entity) {
        this.id = entity.getId();
        this.createdAt = entity.getCreatedAt();
        this.content = entity.getContent();
        this.notificationType = entity.getNotificationType();
        this.targetId = entity.getTargetId();
        this.userId = entity.getUserId();
    }
}
