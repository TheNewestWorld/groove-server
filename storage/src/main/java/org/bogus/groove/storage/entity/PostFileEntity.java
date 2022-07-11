package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_file")
@Getter
@NoArgsConstructor
public class PostFileEntity extends BaseEntity {

  @Column(name = "ref_post_id")
  private Long postId;

  @Column(name = "ref_file_id")
  private Long fileId;

  public PostFileEntity(Long postId, Long fileId) {
    this.postId = postId;
    this.fileId = fileId;
  }
}
