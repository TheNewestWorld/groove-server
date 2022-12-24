package org.bogus.groove.domain.notification;

import java.util.Map;
import org.bogus.groove.common.enumeration.FreemarkerTemplateType;
import org.bogus.groove.common.enumeration.NotificationType;
import org.bogus.groove.common.template.NotificationTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationTemplateFactory {

    public TemplateSend likePost(String userName, String linkUrl) {
        return new TemplateSend(NotificationTemplate.LIKE_POST.getTemplateName(), Map.of("userName", userName),
            FreemarkerTemplateType.PUSH, linkUrl, NotificationType.LIKE);
    }

    public TemplateSend commentPost(String userName, String linkUrl) {
        return new TemplateSend(NotificationTemplate.COMMENT_POST.getTemplateName(), Map.of("userName", userName),
            FreemarkerTemplateType.PUSH, linkUrl, NotificationType.COMMENT);
    }
}
