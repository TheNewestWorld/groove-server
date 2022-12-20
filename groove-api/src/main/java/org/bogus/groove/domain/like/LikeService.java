package org.bogus.groove.domain.like;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.client.user.UserClient;
import org.bogus.groove.domain.notification.NotificationCreator;
import org.bogus.groove.domain.notification.NotificationTemplateFactory;
import org.bogus.groove.domain.notification.TemplateSend;
import org.bogus.groove.domain.notification.TemplateSender;
import org.bogus.groove.domain.post.Post;
import org.bogus.groove.domain.post.PostReader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeCreator likeCreator;
    private final LikeDeleter likeDeleter;
    private final PostReader postReader;
    private final UserClient userClient;
    private final NotificationTemplateFactory templateFactory;
    private final TemplateSender templateSender;

    public void like(Long userId, Long postId) throws IOException {
        likeCreator.create(userId, postId);
        var commentUser = userClient.get(userId);
        Post post = postReader.readPost(postId);
        TemplateSend dto = templateFactory.likePost(commentUser.getNickname(), "/api/community/post/" + postId);
        templateSender.notiAsync(post.getUserId(), dto);
    }

    public void unLike(Long userId, Long postId) {
        likeDeleter.delete(userId, postId);
    }
}
