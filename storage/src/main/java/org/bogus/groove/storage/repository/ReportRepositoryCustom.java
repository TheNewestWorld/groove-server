package org.bogus.groove.storage.repository;

import org.bogus.groove.storage.entity.ReportEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReportRepositoryCustom {
    Slice<ReportEntity> reportAllPosts(Pageable pageable);
}
