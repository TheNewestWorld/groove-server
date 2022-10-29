package org.bogus.groove.storage.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.PostEntity;
import org.bogus.groove.storage.entity.QPostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<PostEntity> findAllPosts(Long categoryId, Pageable pageable, String word) {
        QPostEntity post = QPostEntity.postEntity;
        List<PostEntity> results = jpaQueryFactory.selectFrom(post).where(post.categoryId.eq(categoryId), post.content.contains(word)).orderBy(sort(pageable)).limit(pageable.getPageSize()+1).offset(pageable.getOffset()).fetch();

        boolean hasNext = false;
        if(results.size() > pageable.getPageSize()) {
            results.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(results, pageable, hasNext);
    }

    private OrderSpecifier<?> sort(Pageable pageable) {
        QPostEntity post = QPostEntity.postEntity;
        if(!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()) {
                    case "createdAt":
                        return new OrderSpecifier<>(direction, post.createdAt);
                    case "likeCount":
                        return new OrderSpecifier<>(direction, post.likeCount);
                    case "commentCount":
                        return new OrderSpecifier<>(direction, post.commentCount);
                }
            }
        }
        return null;
    }


}
