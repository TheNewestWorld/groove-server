package org.bogus.groove.domain.post;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.comment.CommentReader;
import org.bogus.groove.endpoint.comment.CommentResponse;
import org.bogus.groove.endpoint.post.PostDetailResponse;
import org.bogus.groove.endpoint.post.PostResponse;
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

    public List<PostResponse> getPostList(Pageable pageable) {
        List<PostResponse> postList = postReader.readAllPosts(pageable).stream().map(
            post -> new PostResponse(post, commentReader.countPostComment(post.getId()))).collect(Collectors.toList());
        return postList;
    }

    public List<PostResponse> getPostList(Long categoryId, Pageable pageable) {
        List<PostResponse> postList = postReader.readAllPosts(categoryId, pageable).stream().map(
            post -> new PostResponse(post, commentReader.countPostComment(post.getId()))).collect(Collectors.toList());
        return postList;
    }

    public PostDetailResponse getPost(Long postId) {
        Post post = postReader.readPost(postId);
        PostResponse postResponse = new PostResponse(post, commentReader.countPostComment(postId));

        List<CommentResponse> comments =
            commentReader.readAllPostComment(postId).stream()
                .map(comment -> new CommentResponse(comment, commentReader.readAllPostReComment(
                    comment.getId()).stream().map(recomment -> new CommentResponse(recomment)).collect(Collectors.toList()))).collect(
                    Collectors.toList());

        PostDetailResponse postDetail = new PostDetailResponse(postResponse, comments);
        return postDetail;
    }

    public void updatePost(Long userId, Long postId, String title, String content, Long categoryId) {
        postUpdater.updatePost(userId, postId, title, content, categoryId);
    }

    public void deletePost(Long userId, Long postId) {
        postDeleter.deletePost(userId, postId);
    }
}
