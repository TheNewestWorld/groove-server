package org.bogus.groove.endpoint.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bogus.groove.common.enumeration.CategoryGroup;
import org.bogus.groove.domain.category.CategoryGetResult;

@Getter
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private CategoryGroup categoryGroup;

    public CategoryResponse(CategoryGetResult category) {
        this.id = category.getId();
        this.name = category.getName();
        this.categoryGroup = category.getCategoryGroup();
    }
}
