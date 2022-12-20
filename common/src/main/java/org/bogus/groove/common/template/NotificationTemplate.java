package org.bogus.groove.common.template;

public enum NotificationTemplate {
    LIKE_POST("likePost"),
    COMMENT_POST("commentPost");

    private final String templateName;

    NotificationTemplate(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return templateName;
    }
}
