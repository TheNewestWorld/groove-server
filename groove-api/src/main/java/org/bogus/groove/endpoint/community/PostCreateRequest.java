package org.bogus.groove.endpoint.community;

import lombok.Getter;

@Getter
public class CommunityCreateRequest {

    public class Post {
        private String title;

        private String content;

        private Integer likeCount;

        private boolean isTemporary;

        private Long userId;

        private Long categoryId;
    }

    public class Comment {
        private String content;

        private Long userId;

        private Long postId;
    }

}
