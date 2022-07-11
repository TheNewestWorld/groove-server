package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor
public class PostEntity extends BaseEntity {

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @Column(name = "like_count")
  private Integer likeCount;

  @Column(name = "temporary_flag")
  private boolean isTemporary;

  @Column(name = "ref_user_id")
  private Long userId;

  @Column(name = "ref_category_id")
  private Long categoryId;

  public PostEntity(String title, String content, Integer likeCount, boolean isTemporary, Long userId, Long categoryId) {
    this.title = title;
    this.content = content;
    this.likeCount = likeCount;
    this.isTemporary = isTemporary;
    this.userId = userId;
    this.categoryId = categoryId;
  }
}
