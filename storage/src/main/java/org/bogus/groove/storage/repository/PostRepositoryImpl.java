package org.bogus.groove.storage.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.SortOrderType;
import org.bogus.groove.storage.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<PostEntity> findAllPosts(Long categoryId, PageRequest of, SortOrderType sortOrderType, String word) {
        return null;
    }
}
