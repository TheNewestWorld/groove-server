package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor
public class PostEntity extends BaseEntity {

    @Column(name = "title")
    @Setter
    private String title;

    @Column(name = "content")
    @Setter
    private String content;

    @Column(name = "deleted_flag")
    @Setter
    private boolean isDeleted;

    @Column(name = "like_count")
    @Setter
    private Integer likeCount;

    @Column(name = "comment_count")
    @Setter
    private Integer commentCount;

    @Column(name = "ref_user_id")
    private Long userId;

    @Column(name = "ref_category_id")
    @Setter
    private Long categoryId;

    public PostEntity(String title, String content, boolean isDeleted,
                      Long userId, Long categoryId) {
        super();
        this.title = title;
        this.content = content;
        this.isDeleted = isDeleted;
        this.userId = userId;
        this.categoryId = categoryId;
    }
}
