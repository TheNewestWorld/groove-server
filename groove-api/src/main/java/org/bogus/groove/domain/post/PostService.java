package org.bogus.groove.domain.post;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.comment.CommentReader;
import org.bogus.groove.domain.like.Like;
import org.bogus.groove.domain.like.LikeReader;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostCreator postCreator;
    private final PostReader postReader;
    private final PostUpdater postUpdater;
    private final PostDeleter postDeleter;
    private final LikeReader likeReader;
    private final CommentReader commentReader;

    public Post createPost(String title, String content, Long userId, Long categoryId) {
        return postCreator.createPost(title, content, userId, categoryId);
    }

    public List<PostGetResult> getPostList(Long userId, Pageable pageable) {
        List<Like> likeList = likeReader.likeList(userId);
        List<PostGetResult> postList = postReader.readAllPosts(pageable).stream().map(
            post -> new PostGetResult(post,
                likeList.stream().filter(like -> like.getPostId() == post.getId()).collect(Collectors.toList()).isEmpty(),
                likeReader.countPostLike(post.getId()),
                commentReader.countPostComment(post.getId()))).collect(Collectors.toList());
        return postList;
    }

    public List<PostGetResult> getPostList(Long userId, Long categoryId, Pageable pageable) {
        List<Like> likeList = likeReader.likeList(userId);
        List<PostGetResult> postList = postReader.readAllPosts(categoryId, pageable).stream().map(
            post -> new PostGetResult(post,
                likeList.stream().filter(like -> like.getPostId() == post.getId()).collect(Collectors.toList()).isEmpty(),
                likeReader.countPostLike(post.getId()),
                commentReader.countPostComment(post.getId()))).collect(Collectors.toList());
        return postList;
    }

    public PostGetDetailResult getPost(Long userId, Long postId) {
        Post post = postReader.readPost(postId);
        PostGetResult result = new PostGetResult(post, likeReader.checkLike(userId, postId), likeReader.countPostLike(postId),
            commentReader.countPostComment(postId));
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
