package org.bogus.groove.domain.like;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.bogus.groove.storage.entity.LikeEntity;

@Getter
@AllArgsConstructor
@ToString
public class Like {
    private Long id;
    private Long userId;
    private Long postId;

    public Like(LikeEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.postId = entity.getPostId();
    }
}
