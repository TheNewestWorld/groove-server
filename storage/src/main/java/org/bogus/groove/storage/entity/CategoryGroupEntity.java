package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category_group")
@Getter
@NoArgsConstructor
public class CategoryGroupEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    public CategoryGroupEntity(String name) {
        this.name = name;
    }
}
