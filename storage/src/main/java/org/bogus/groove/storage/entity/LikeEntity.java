package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "like")
@Getter
@NoArgsConstructor
public class LikeEntity extends BaseEntity {

    @Column(name = "ref_user_id")
    private Long userId;

    @Column(name = "ref_post_id")
    private Long postId;

    public LikeEntity(Long userId, Long postId) {
        super();
        this.userId = userId;
        this.postId = postId;
    }
}
