package org.bogus.groove.storage.repository;

import java.util.Optional;
import org.bogus.groove.storage.entity.FreemarkerTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreemarkerTemplateRepository extends JpaRepository<FreemarkerTemplateEntity, Long> {

    Optional<FreemarkerTemplateEntity> findByNameAndDeleteFlagFalse(String templateName);
}
