package org.bogus.groove.domain.post;

import lombok.Getter;

@Getter
public class PostGetResult {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    //private String nickName;
    //private String profileImg;
    private boolean likeFlag;
    private Integer likeCount;
    private Integer commentCount;

    public PostGetResult(Post post, boolean likeFlag, Integer likeCount, Integer commentCount) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.userId = post.getUserId();
        this.likeFlag = likeFlag;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }
}
