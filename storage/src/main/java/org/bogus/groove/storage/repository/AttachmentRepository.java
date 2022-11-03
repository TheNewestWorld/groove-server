package org.bogus.groove.storage.repository;

import java.util.Optional;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.storage.entity.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<AttachmentEntity, Long> {
    Optional<AttachmentEntity> findByObjectKeyAndAttachmentType(String objectKey, AttachmentType attachmentType);
}
