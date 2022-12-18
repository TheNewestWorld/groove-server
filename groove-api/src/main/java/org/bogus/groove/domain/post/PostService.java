package org.bogus.groove.domain.post;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.client.user.UserClient;
import org.bogus.groove.client.user.UserInfo;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.enumeration.SortOrderType;
import org.bogus.groove.domain.attachment.PostAttachmentCreateParam;
import org.bogus.groove.domain.comment.CommentReader;
import org.bogus.groove.domain.like.Like;
import org.bogus.groove.domain.like.LikeReader;
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
    private final UserClient userClient;
    private final CommentReader commentReader;
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
                    param.getAttachmentType()
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
                post -> {
                    UserInfo userInfo = userClient.get(post.getUserId());
                    return new PostGetResult(
                        post,
                        userInfo.getNickname(),
                        userInfo.getProfileUri(),
                        !likeList.stream().filter(like -> like.getPostId() == post.getId()).collect(Collectors.toList()).isEmpty(),
                        userId == post.getUserId() ? true : false,
                        getAttachmentUri(post));
                }
            ).toList(),
            posts.getPageable(),
            posts.hasNext()
        );
    }

    public PostGetDetailResult getPost(Long userId, Long postId) {
        Post post = postReader.readPost(postId);
        UserInfo userInfo = userClient.get(post.getUserId());
        PostGetResult result = new PostGetResult(
            post,
            userInfo.getNickname(),
            userInfo.getProfileUri(),
            likeReader.checkLike(userId, postId),
            userId == post.getUserId() ? true : false,
            getAttachmentUri(post)
        );
        PostGetDetailResult postDetail = new PostGetDetailResult(result, post.getCreatedAt());
        return postDetail;
    }

    public Slice<MyPostGetResult> getMyPosts(Long userId, int page, int size) {
        var userInfo = userClient.get(userId);
        var posts = postReader.readAllPosts(userId, page, size);

        return posts.map((post) ->
            new MyPostGetResult(
                post,
                userInfo,
                likeReader.checkLike(userInfo.getId(), post.getId()),
                likeReader.countPostLike(post.getId()),
                commentReader.countPostComment(post.getId())
            )
        );
    }

    public Slice<MyPostGetResult> getLikedPosts(Long userId, int page, int size) {
        var userInfo = userClient.get(userId);
        var posts = postReader.readAllLikedPosts(userId, page, size);

        return posts.map((post) ->
            new MyPostGetResult(
                post,
                userInfo,
                likeReader.checkLike(userInfo.getId(), post.getId()),
                likeReader.countPostLike(post.getId()),
                commentReader.countPostComment(post.getId())
            )
        );
    }

    public void updatePost(Long userId, Long postId, String title, String content, Long categoryId,
                           List<PostAttachmentCreateParam> params) {
        var attachments = attachmentReader.readAll(postId, AttachmentType.POST_IMAGE);
        attachments.addAll(attachmentReader.readAll(postId, AttachmentType.POST_RECORD));
        attachments.forEach((attachment -> attachmentDeleter.delete(attachment.getId())));
        postUpdater.updatePost(userId, postId, title, content, categoryId);

        for (PostAttachmentCreateParam param : params) {
            attachmentUploader.upload(
                new AttachmentUploadParam(
                    param.getInputStream(),
                    param.getFileName(),
                    param.getSize(),
                    postId,
                    param.getAttachmentType()
                )
            );
        }
    }

    public void deletePost(Long userId, Long postId) {
        postDeleter.deletePost(userId, postId);
        var attachments = attachmentReader.readAll(postId, AttachmentType.POST_IMAGE);
        attachments.addAll(attachmentReader.readAll(postId, AttachmentType.POST_RECORD));
        attachments.forEach((attachment -> attachmentDeleter.delete(attachment.getId())));
    }

    private List<Attachment> getAttachmentUri(Post post) {
        var attachments = attachmentReader.readAll(post.getId(), AttachmentType.POST_IMAGE);
        attachments.addAll(attachmentReader.readAll(post.getId(), AttachmentType.POST_RECORD));
        return attachments;
    }
}
