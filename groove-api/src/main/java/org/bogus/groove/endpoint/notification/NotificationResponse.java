package org.bogus.groove.endpoint.notification;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bogus.groove.common.enumeration.NotificationType;
import org.bogus.groove.domain.notification.NotificationGetResult;

@Getter
@AllArgsConstructor
public class NotificationResponse {
    private Long id;
    private LocalDateTime createdAt;
    private String content;
    private NotificationType notificationType;
    private boolean readFlag;
    private boolean deleteFlag;
    private String linkUrl;
    private Long userId;
    private String profileUri;

    public NotificationResponse(NotificationGetResult notification) {
        this.id = notification.getId();
        this.createdAt = notification.getCreatedAt();
        this.content = notification.getContent();
        this.notificationType = notification.getNotificationType();
        this.readFlag = notification.isReadFlag();
        this.deleteFlag = notification.isDeleteFlag();
        this.linkUrl = notification.getLinkUrl();
        this.userId = notification.getTargetUserId();
        this.profileUri = notification.getProfileUri();
    }
}
