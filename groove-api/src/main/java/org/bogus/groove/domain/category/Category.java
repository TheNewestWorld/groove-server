package org.bogus.groove.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bogus.groove.common.enumeration.CategoryGroup;
import org.bogus.groove.storage.entity.CategoryEntity;

@Getter
@AllArgsConstructor
@ToString
public class Category {
    private Long id;
    private String name;
    private CategoryGroup categoryGroup;

    public Category(CategoryEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.categoryGroup = entity.getCategoryGroup();
    }
}
