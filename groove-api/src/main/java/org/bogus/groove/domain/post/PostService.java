package org.bogus.groove.domain.post;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.client.user.UserClient;
import org.bogus.groove.client.user.UserInfo;
import org.bogus.groove.common.enumeration.SortOrderType;
import org.bogus.groove.domain.like.Like;
import org.bogus.groove.domain.like.LikeReader;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostCreator postCreator;
    private final PostReader postReader;
    private final PostUpdater postUpdater;
    private final PostDeleter postDeleter;
    private final LikeReader likeReader;
    private final UserClient userClient;

    public Post createPost(String title, String content, Long userId, Long categoryId) {
        return postCreator.createPost(title, content, userId, categoryId);
    }

    public Slice<PostGetResult> getPostList(Long userId, Long categoryId, int page, int size, SortOrderType sortOrderType, String word) {
        UserInfo userInfo = userClient.get(userId);
        List<Like> likeList = likeReader.likeList(userId);
        var posts = postReader.readAllPosts(categoryId, page, size, sortOrderType, word);
        return new SliceImpl<>(
            posts.map(
                post -> new PostGetResult(post, userInfo.getNickname(),
                    !likeList.stream().filter(like -> like.getPostId() == post.getId()).collect(Collectors.toList()).isEmpty())).toList(),
            posts.getPageable(),
            posts.hasNext()
        );
    }

    public PostGetDetailResult getPost(Long userId, Long postId) {
        UserInfo userInfo = userClient.get(userId);
        Post post = postReader.readPost(postId);
        PostGetResult result = new PostGetResult(post, userInfo.getNickname(), likeReader.checkLike(userId, postId));
        PostGetDetailResult postDetail = new PostGetDetailResult(result, post.getCreatedAt());
        return postDetail;
    }

    public void updatePost(Long userId, Long postId, String title, String content, Long categoryId) {
        postUpdater.updatePost(userId, postId, title, content, categoryId);
    }

    public void deletePost(Long userId, Long postId) {
        postDeleter.deletePost(userId, postId);
    }
}
