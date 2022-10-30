package org.bogus.groove.endpoint.category;

import lombok.Getter;
import org.bogus.groove.common.enumeration.CategoryGroup;

@Getter
public class CategoryUpdateRequest {
    private String name;
    private CategoryGroup categoryGroup;
}
