package org.bogus.groove.endpoint.post;

import lombok.Getter;
import org.bogus.groove.domain.post.MyPostGetResult;

@Getter
public class MyPostGetResponse {
    private final Long postId;
    private final Long userId;
    private final String userNickname;
    private final String userProfileUri;
    private final String title;
    private final String content;
    private final int likeCount;
    private final int commentCount;

    MyPostGetResponse(MyPostGetResult result) {
        this.postId = result.getPostId();
        this.userId = result.getUserId();
        this.userNickname = result.getUserNickname();
        this.userProfileUri = result.getUserProfileUri();
        this.title = result.getTitle();
        this.content = result.getContent();
        this.likeCount = result.getLikeCount();
        this.commentCount = result.getCommentCount();
    }
}
