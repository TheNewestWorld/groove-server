package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "something")
@Getter
@NoArgsConstructor
public class SomethingEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    @Setter
    private Integer age;

    public SomethingEntity(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }
}
