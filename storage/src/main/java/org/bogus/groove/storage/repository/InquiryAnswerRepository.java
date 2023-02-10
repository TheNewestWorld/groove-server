package org.bogus.groove.storage.repository;

import java.util.Optional;
import org.bogus.groove.storage.entity.InquiryAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryAnswerRepository extends JpaRepository<InquiryAnswerEntity, Long> {
    Optional<InquiryAnswerEntity> findByInquiryId(Long inquiryId);
}
