package org.bogus.groove.domain.notification;

import java.time.LocalDateTime;
import lombok.Getter;
import org.bogus.groove.common.enumeration.NotificationType;

@Getter
public class NotificationGetResult {

    private Long id;
    private LocalDateTime createdAt;
    private String content;
    private NotificationType notificationType;
    private boolean readFlag;
    private boolean deleteFlag;
    private String linkUrl;
    private Long userId;
    private String profileUri;

    public NotificationGetResult(Notification notification, String profileUri) {
        this.id = notification.getId();
        this.createdAt = notification.getCreatedAt();
        this.content = notification.getContent();
        this.notificationType = notification.getNotificationType();
        this.readFlag = notification.isReadFlag();
        this.deleteFlag = notification.isDeleteFlag();
        this.linkUrl = notification.getLinkUrl();
        this.userId = notification.getUserId();
        this.profileUri = profileUri;
    }
}
