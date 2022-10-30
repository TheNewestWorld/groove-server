package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bogus.groove.common.enumeration.CategoryGroup;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor
public class CategoryEntity extends BaseEntity {

    @Column(name = "name")
    @Setter
    private String name;

    @Column(name = "category_group")
    @Enumerated(EnumType.STRING)
    @Setter
    private CategoryGroup categoryGroup;

    @Column(name = "deleted_flag")
    @Setter
    private boolean isDeleted;

    public CategoryEntity(String name, CategoryGroup categoryGroup, boolean isDeleted) {
        this.name = name;
        this.categoryGroup = categoryGroup;
        this.isDeleted = isDeleted;
    }
}
