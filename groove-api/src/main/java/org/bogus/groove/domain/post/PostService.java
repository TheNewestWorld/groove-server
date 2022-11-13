package org.bogus.groove.domain.post;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.enumeration.SortOrderType;
import org.bogus.groove.domain.attachment.PostAttachmentCreateParam;
import org.bogus.groove.domain.like.Like;
import org.bogus.groove.domain.like.LikeReader;
import org.bogus.groove.domain.user.User;
import org.bogus.groove.domain.user.UserReader;
import org.bogus.groove.object_storage.Attachment;
import org.bogus.groove.object_storage.AttachmentDeleter;
import org.bogus.groove.object_storage.AttachmentReader;
import org.bogus.groove.object_storage.AttachmentUploadParam;
import org.bogus.groove.object_storage.AttachmentUploader;
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
    private final UserReader userReader;
    private final AttachmentReader attachmentReader;
    private final AttachmentUploader attachmentUploader;
    private final AttachmentDeleter attachmentDeleter;

    public Post createPost(String title, String content, Long userId, Long categoryId, List<PostAttachmentCreateParam> params) {
        var post = postCreator.createPost(title, content, userId, categoryId);

        for (PostAttachmentCreateParam param : params) {
            attachmentUploader.upload(
                new AttachmentUploadParam(
                    param.getInputStream(),
                    param.getFileName(),
                    param.getSize(),
                    post.getId(),
                    AttachmentType.POST
                )
            );
        }

        return post;
    }

    public Slice<PostGetResult> getPostList(Long userId, Long categoryId, int page, int size, SortOrderType sortOrderType, String word) {
        List<Like> likeList = likeReader.likeList(userId);
        var posts = postReader.readAllPosts(categoryId, page, size, sortOrderType, word);
        return new SliceImpl<>(
            posts.map(
                post -> new PostGetResult(
                    post,
                    userReader.read(post.getUserId()).getNickname(),
                    getProfileUri(userId),
                    !likeList.stream().filter(like -> like.getPostId() == post.getId()).collect(Collectors.toList()).isEmpty(),
                    userId == post.getUserId() ? true : false,
                    getAttachmentUri(post))
            ).toList(),
            posts.getPageable(),
            posts.hasNext()
        );
    }

    public PostGetDetailResult getPost(Long userId, Long postId) {
        User user = userReader.read(userId);
        Post post = postReader.readPost(postId);
        PostGetResult result = new PostGetResult(
            post,
            user.getNickname(),
            getProfileUri(userId),
            likeReader.checkLike(userId, postId),
            userId == post.getUserId() ? true : false,
            getAttachmentUri(post)
        );
        PostGetDetailResult postDetail = new PostGetDetailResult(result, post.getCreatedAt());
        return postDetail;
    }

    public void updatePost(Long userId, Long postId, String title, String content, Long categoryId,
                           List<PostAttachmentCreateParam> params) {
        var attachments = attachmentReader.readAll(postId, AttachmentType.POST);
        attachments.forEach((attachment -> attachmentDeleter.delete(attachment.getId())));
        postUpdater.updatePost(userId, postId, title, content, categoryId);

        for (PostAttachmentCreateParam param : params) {
            attachmentUploader.upload(
                new AttachmentUploadParam(
                    param.getInputStream(),
                    param.getFileName(),
                    param.getSize(),
                    postId,
                    AttachmentType.POST
                )
            );
        }
    }

    public void deletePost(Long userId, Long postId) {
        postDeleter.deletePost(userId, postId);
        var attachments = attachmentReader.readAll(postId, AttachmentType.POST);
        attachments.forEach((attachment -> attachmentDeleter.delete(attachment.getId())));
    }

    private String getProfileUri(Long userId) {
        return attachmentReader.readAll(userId, AttachmentType.PROFILE)
            .stream().findFirst().map(Attachment::getUri).orElse(null);
    }

    private List<String> getAttachmentUri(Post post) {
        return attachmentReader.readAll(post.getId(), AttachmentType.POST).stream().map(Attachment::getUri).collect(Collectors.toList());
    }
}
