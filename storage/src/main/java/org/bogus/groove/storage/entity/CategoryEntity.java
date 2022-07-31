package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor
public class CategoryEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "ref_category_group_id")
    private Long categoryGroupId;

    public CategoryEntity(String name, Long categoryGroupId) {
        this.name = name;
        this.categoryGroupId = categoryGroupId;
    }
}
