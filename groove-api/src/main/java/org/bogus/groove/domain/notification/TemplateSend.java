package org.bogus.groove.domain.notification;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;
import org.bogus.groove.common.enumeration.FreemarkerTemplateType;
import org.bogus.groove.common.enumeration.NotificationType;

@Getter
public class TemplateSend {

    private String templateName;
    private Map<String, String> variables;
    private FreemarkerTemplateType templateType;
    private String linkUrl;
    private NotificationType notificationType;

    private String output;
    private String templateTitle;
    private String groupKey;
    private boolean groupFlag = false;
    private Map<String, String> usingVariables = new HashMap<>();

    public void setAsGroupSend() {
        this.groupKey = RandomStringUtils.randomAlphabetic(8);
        this.groupFlag = true;
    }

    public void setTemplateTitle(String title) {
        this.templateTitle = title;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public TemplateSend(String templateName, Map<String, String> variables, FreemarkerTemplateType templateType, String linkUrl,
                        NotificationType notificationType) {
        this.templateName = templateName;
        this.variables = variables;
        this.templateType = templateType;
        this.linkUrl = linkUrl;
        this.notificationType = notificationType;
    }
}
