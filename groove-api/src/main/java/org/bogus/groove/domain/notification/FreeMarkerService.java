package org.bogus.groove.domain.notification;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bogus.groove.common.exception.BadRequestException;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.InternalServerException;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.entity.FreemarkerTemplateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Component
public class FreeMarkerService {

    private final Pattern templateVariablePattern = Pattern.compile("\\$\\{([^}]+)\\}");
    private final Configuration cfg;
    private final FreemarkerTemplateDBLoader templateDBLoader;

    @Autowired
    public FreeMarkerService(Configuration cfg, FreemarkerTemplateDBLoader templateDBLoader) {
        this.cfg = cfg;
        this.templateDBLoader = templateDBLoader;
        cfg.setTemplateLoader(templateDBLoader);
    }

    public TemplateSend generateOutput(TemplateSend dto) throws InternalServerException, IOException {
        FreemarkerTemplateEntity entity = validateTemplateThenGet(dto);
        dto.setTemplateTitle(entity.getTitle());

        try {
            Template template = cfg.getTemplate(dto.getTemplateName(), Locale.KOREA);
            String output = FreeMarkerTemplateUtils.processTemplateIntoString(template, dto.getVariables());
            dto.setOutput(output);
            return dto;
        } catch (IOException | TemplateException e) {
            throw new InternalServerException(ErrorType.FAILED_TO_CONVERT_TEMPLATE);
        }

    }

    public FreemarkerTemplateEntity validateTemplateThenGet(TemplateSend dto) throws IOException {
        FreemarkerTemplateEntity entity = (FreemarkerTemplateEntity) templateDBLoader.findTemplateSource(dto.getTemplateName());

        if (entity == null) {
            throw new NotFoundException(ErrorType.NOT_FOUND_TEMPLATE);
        }

        Set<String> templateVariables = getTemplateVariables(entity.getContent());

        if (dto.getTemplateType() != entity.getType()) {
            throw new BadRequestException(ErrorType.TEMPLATE_TYPE_WRONG);
        }

        //variables 변수 없는 템플릿 체크
        Map<String, String> variables = dto.getVariables();
        if (!templateVariables.isEmpty() && variables.isEmpty()) {
            throw new BadRequestException(ErrorType.TEMPLATE_VARIABLES_EMPTY);
        }

        for (String v : templateVariables) {
            if (variables.get(v) == null) {
                throw new NotFoundException(ErrorType.NOT_FOUND_TEMPLATE_VARIABLE);
            }
            dto.getUsingVariables().put(v, variables.get(v));
        }

        return entity;
    }

    public Set<String> getTemplateVariables(String templateContent) {
        Set<String> variables = new HashSet<>();
        Matcher matcher = templateVariablePattern.matcher(templateContent);
        while (matcher.find()) {
            variables.add(matcher.group(1));
        }
        return variables;
    }
}
