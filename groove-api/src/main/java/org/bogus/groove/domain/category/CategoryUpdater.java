package org.bogus.groove.domain.category;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.CategoryGroup;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryUpdater {
    private final CategoryRepository categoryRepository;

    @Transactional
    public Category updateCategory(Long categoryId, String name, CategoryGroup categoryGroup) {
        var entity = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_CATEGORY));
        entity.setName(name);
        entity.setCategoryGroup(categoryGroup);
        return new Category(entity);
    }
}
