package org.bogus.groove.storage.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.NotificationEntity;
import org.bogus.groove.storage.entity.QNotificationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<NotificationEntity> findAllNotifications(Long userId, Pageable pageable) {
        QNotificationEntity notification = QNotificationEntity.notificationEntity;
        List<NotificationEntity> results =
            jpaQueryFactory.selectFrom(notification).where(notification.userId.eq(userId))
                .orderBy(new OrderSpecifier<>(Order.DESC, notification.createdAt))
                .limit(pageable.getPageSize() + 1).offset(pageable.getOffset()).fetch();

        boolean hasNext = false;
        if (results.size() > pageable.getPageSize()) {
            results.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(results, pageable, hasNext);
    }
}