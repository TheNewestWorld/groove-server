package org.bogus.groove.storage.repository;

import org.bogus.groove.storage.entity.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<AttachmentEntity, Long> { }
