package org.bogus.groove.domain.post;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.comment.CommentReader;
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
        List<PostResponse> postList = postReader.readAllPost(pageable).stream().map(
            post -> new PostResponse(post, commentReader.countComment(post.getId()))).collect(Collectors.toList());
        return postList;
    }

    public Post getPost(Long postId) {
        return postReader.readPost(postId);
    }

    public void updatePost(Long postId, String title, String content, Long categoryId) {
        postUpdater.updatePost(postId, title, content, categoryId);
    }

    public void deletePost(Long postId) {
        postDeleter.deletePost(postId);
    }
}
