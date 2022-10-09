package org.bogus.groove.domain.post;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.comment.CommentReader;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostCreator postCreator;
    private final PostReader postReader;
    private final PostUpdater postUpdater;
    private final PostDeleter postDeleter;
    private final CommentReader commentReader;

    public Post createPost(String title, String content, Integer likeCount, Long userId, Long categoryId) {
        return postCreator.createPost(title, content, likeCount, userId, categoryId);
    }

    public List<PostGetResult> getPostList(Pageable pageable) {
        List<PostGetResult> postList = postReader.readAllPosts(pageable).stream().map(
            post -> new PostGetResult(post, commentReader.countPostComment(post.getId()))).collect(Collectors.toList());
        return postList;
    }

    public List<PostGetResult> getPostList(Long categoryId, Pageable pageable) {
        List<PostGetResult> postList = postReader.readAllPosts(categoryId, pageable).stream().map(
            post -> new PostGetResult(post, commentReader.countPostComment(post.getId()))).collect(Collectors.toList());
        return postList;
    }

    public PostGetDetailResult getPost(Long postId) {
        Post post = postReader.readPost(postId);
        PostGetResult result = new PostGetResult(post, commentReader.countPostComment(postId));
        PostGetDetailResult postDetail = new PostGetDetailResult(result);
        return postDetail;
    }

    public void updatePost(Long userId, Long postId, String title, String content, Long categoryId) {
        postUpdater.updatePost(userId, postId, title, content, categoryId);
    }

    public void deletePost(Long userId, Long postId) {
        postDeleter.deletePost(userId, postId);
    }
}
