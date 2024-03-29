package org.bogus.groove.domain.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.client.user.UserInfo;

@Getter
@RequiredArgsConstructor
public class MyPostGetResult {
    private final Long postId;
    private final Long userId;
    private final String userNickname;
    private final String userProfileUri;
    private final String title;
    private final String content;
    private final boolean likeFlag;
    private final int likeCount;
    private final int commentCount;

    MyPostGetResult(Post post, UserInfo userInfo, boolean likeFlag, int likeCount, int commentCount) {
        this.postId = post.getId();
        this.userId = post.getUserId();
        this.userNickname = userInfo.getNickname();
        this.userProfileUri = userInfo.getProfileUri();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.likeFlag = likeFlag;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }
}
