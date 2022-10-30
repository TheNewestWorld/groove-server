package org.bogus.groove.domain.post;

import lombok.Getter;

@Getter
public class PostGetResult {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String nickName;
    //private String profileImg;
    private boolean likeFlag;
    private Integer likeCount;
    private Integer commentCount;
    private Long categoryId;

    public PostGetResult(Post post, String nickName, boolean likeFlag) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUserId();
        this.nickName = nickName;
        this.likeFlag = likeFlag;
        this.likeCount = post.getLikeCount();
        this.commentCount = post.getCommentCount();
        this.categoryId = post.getCategoryId();
    }
}
