package org.bogus.groove.domain.category;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.CategoryGroup;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.InternalServerException;
import org.bogus.groove.storage.entity.CategoryEntity;
import org.bogus.groove.storage.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCreator {
    private final CategoryRepository categoryRepository;

    public Category createCategory(String name, CategoryGroup categoryGroup) {
        try {
            var entity = categoryRepository.save(new CategoryEntity(name, categoryGroup, false));
            return new Category(entity);
        } catch (IllegalArgumentException e) {
            throw new InternalServerException(ErrorType.FAILED_TO_CREATE_CATEGORY);
        }
    }
}
