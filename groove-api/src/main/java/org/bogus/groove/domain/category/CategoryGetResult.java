package org.bogus.groove.domain.category;

import lombok.Getter;
import org.bogus.groove.common.enumeration.CategoryGroup;

@Getter
public class CategoryGetResult {
    private Long id;
    private String name;
    private CategoryGroup categoryGroup;

    public CategoryGetResult(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.categoryGroup = category.getCategoryGroup();
    }
}
