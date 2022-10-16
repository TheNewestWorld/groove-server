package org.bogus.groove.domain.like;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.repository.LikeRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeReader {
    private final LikeRepository likeRepository;

    public List<Like> likeList(Long userId) {
        return likeRepository.findByUserId(userId).stream().map(entity -> new Like(entity)).collect(Collectors.toList());
    }

    public boolean checkLike(Long userId, Long postId) {
        return likeRepository.findByUserIdAndPostId(userId, postId).isPresent();
    }

    public Integer countPostLike(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}
