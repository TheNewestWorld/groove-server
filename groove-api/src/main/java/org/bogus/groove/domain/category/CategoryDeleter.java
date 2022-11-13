package org.bogus.groove.domain.category;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.bogus.groove.storage.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDeleter {
    private final CategoryRepository categoryRepository;

    @Transactional
    public void deleteCategory(Long categoryId) {
        var entity = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_COMMENT));
        entity.setDeleted(true);
    }
}
