package org.bogus.groove.storage.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.entity.PostEntity;
import org.bogus.groove.storage.entity.QLikeEntity;
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
        List<PostEntity> results =
            jpaQueryFactory.selectFrom(post).where(allCheck(categoryId, word)).orderBy(sort(pageable))
                .limit(pageable.getPageSize() + 1).offset(pageable.getOffset()).fetch();

        boolean hasNext = false;
        if (results.size() > pageable.getPageSize()) {
            results.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(results, pageable, hasNext);
    }

    @Override
    public Slice<PostEntity> findAllLikedPosts(Long userId, Pageable pageable) {
        QPostEntity post = QPostEntity.postEntity;
        QLikeEntity like = QLikeEntity.likeEntity;

        var result = jpaQueryFactory.selectFrom(post)
            .join(like).on(like.postId.eq(post.id))
            .where(
                allCheck(null, null)
                    .and(like.userId.eq(userId))
            )
            .orderBy(post.createdAt.desc())
            .limit(pageable.getPageSize() + 1)
            .offset(pageable.getOffset())
            .fetch();

        return new SliceImpl<>(
            result.stream().limit(pageable.getPageSize()).collect(Collectors.toList()),
            pageable,
            result.size() > pageable.getPageSize()
        );
    }

    private BooleanBuilder categoryEq(Long categoryId) {
        QPostEntity post = QPostEntity.postEntity;
        return categoryId != null ? new BooleanBuilder(post.categoryId.eq(categoryId)) : new BooleanBuilder();
    }

    private BooleanBuilder wordSearch(String word) {
        QPostEntity post = QPostEntity.postEntity;
        return word != null ? new BooleanBuilder(post.title.contains(word).or(post.content.contains(word))) : new BooleanBuilder();
    }

    private BooleanBuilder allCheck(Long categoryId, String word) {
        QPostEntity post = QPostEntity.postEntity;
        return categoryEq(categoryId).and(wordSearch(word)).and(post.isDeleted.eq(Boolean.FALSE));
    }

    private OrderSpecifier<?> sort(Pageable pageable) {
        QPostEntity post = QPostEntity.postEntity;
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()) {
                    case "createdAt":
                        return new OrderSpecifier<>(direction, post.createdAt);
                    case "likeCount":
                        return new OrderSpecifier<>(direction, post.likeCount);
                    case "commentCount":
                        return new OrderSpecifier<>(direction, post.commentCount);
                    default:
                        return new OrderSpecifier<>(direction, post.createdAt);
                }
            }
        }
        return null;
    }


}
