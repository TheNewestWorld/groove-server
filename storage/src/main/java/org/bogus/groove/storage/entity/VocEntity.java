package org.bogus.groove.storage.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "voc")
@Getter
@NoArgsConstructor
public class VocEntity extends BaseEntity {

    @Column(name = "ref_user_id")
    private Long userId;

    @Column(name = "content")
    private String content;

    public VocEntity(Long userId, String content) {
        super();
        this.userId = userId;
        this.content = content;
    }
}
