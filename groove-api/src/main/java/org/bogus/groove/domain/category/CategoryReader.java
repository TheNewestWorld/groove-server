package org.bogus.groove.domain.category;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.CategoryGroup;
import org.bogus.groove.storage.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryReader {
    private final CategoryRepository categoryRepository;

    public List<Category> readAllCategory(CategoryGroup categoryGroup) {
        return categoryRepository.findAllByCategoryGroupAndIsDeletedFalse(categoryGroup).stream().map(Category::new)
            .collect(Collectors.toList());
    }
}
