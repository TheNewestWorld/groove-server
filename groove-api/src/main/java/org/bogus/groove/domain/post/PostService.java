package org.bogus.groove.domain.post;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostCreator postCreator;
    private final PostReader postReader;
    private final PostUpdater postUpdater;
    private final PostDeleter postDeleter;

    public Post createPost(String title, String content, Integer likeCount, Long userId, Long categoryId) {
        return postCreator.createPost(title, content, likeCount, userId, categoryId);
    }

    public List<Post> getPostList() {
        return postReader.readAllPost();
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
