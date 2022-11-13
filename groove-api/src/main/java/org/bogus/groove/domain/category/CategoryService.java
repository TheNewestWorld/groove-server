package org.bogus.groove.domain.category;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.CategoryGroup;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryCreator categoryCreator;
    private final CategoryReader categoryReader;
    private final CategoryUpdater categoryUpdater;
    private final CategoryDeleter categoryDeleter;

    public Category createCategory(String name, CategoryGroup categoryGroup) {
        return categoryCreator.createCategory(name, categoryGroup);
    }

    public List<CategoryGetResult> getCategoryList(CategoryGroup categoryGroup) {
        return categoryReader.readAllCategory(categoryGroup).stream().map(CategoryGetResult::new).collect(Collectors.toList());
    }

    public Category updateCategory(Long categoryId, String name, CategoryGroup categoryGroup) {
        return categoryUpdater.updateCategory(categoryId, name, categoryGroup);
    }

    public void deleteCategory(Long categoryId) {
        categoryDeleter.deleteCategory(categoryId);
    }
}
