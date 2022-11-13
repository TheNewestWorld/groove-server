package org.bogus.groove.storage.repository;

import java.util.List;
import java.util.Optional;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.storage.entity.AttachmentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<AttachmentEntity, Long> {
    Optional<AttachmentEntity> findByObjectKeyAndAttachmentTypeAndIsDeletedIsFalse(String objectKey, AttachmentType attachmentType);

    List<AttachmentEntity> findAllByResourceIdAndAttachmentTypeAndIsDeletedIsFalse(Long resourceId, AttachmentType attachmentType);

    Slice<AttachmentEntity> findAllByResourceIdAndAttachmentTypeAndIsDeletedIsFalse(
        Long resourceId,
        AttachmentType attachmentType,
        Pageable pageable
    );
}
