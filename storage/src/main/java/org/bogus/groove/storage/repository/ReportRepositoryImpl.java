package org.bogus.groove.storage.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.QReportEntity;
import org.bogus.groove.storage.entity.ReportEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReportRepositoryImpl implements ReportRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<ReportEntity> reportAllPosts(Pageable pageable) {
        QReportEntity report = QReportEntity.reportEntity;
        List<ReportEntity> result =
            jpaQueryFactory.selectFrom(report).limit(pageable.getPageSize()).offset(pageable.getOffset()).orderBy(report.id.desc()).fetch();
        return new SliceImpl<>(result, pageable, false);
    }
}
