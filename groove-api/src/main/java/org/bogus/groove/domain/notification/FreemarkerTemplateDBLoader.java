package org.bogus.groove.domain.notification;

import freemarker.cache.TemplateLoader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.FreemarkerTemplateEntity;
import org.bogus.groove.storage.repository.FreemarkerTemplateRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FreemarkerTemplateDBLoader implements TemplateLoader {

    private final FreemarkerTemplateRepository templateRepository;

    @Override
    public Object findTemplateSource(String name) {
        FreemarkerTemplateEntity entity = templateRepository.findByNameAndDeleteFlagFalse(name).orElse(null);
        return entity;
    }

    @Override
    public long getLastModified(Object templateSource) {
        FreemarkerTemplateEntity entity = (FreemarkerTemplateEntity) templateSource;
        return entity.getUpdatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @Override
    public Reader getReader(Object templateSource, String encoding) throws IOException {
        FreemarkerTemplateEntity entity = (FreemarkerTemplateEntity) templateSource;
        return new StringReader(entity.getContent());
    }

    @Override
    public void closeTemplateSource(Object templateSource) throws IOException {

    }
}
