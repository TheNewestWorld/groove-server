package org.bogus.groove.domain.like;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.storage.repository.LikeRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeReader {
    private final LikeRepository likeRepository;

    public List<Like> likeList(Long userId) {
        return likeRepository.findByUserId(userId).stream().map(entity -> new Like(entity)).collect(Collectors.toList());
    }

    public Like read(Long userId, Long postId) {
        return readOrNull(userId, postId).orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_LIKE));
    }

    private Optional<Like> readOrNull(Long userId, Long postId) {
        return likeRepository.findByUserIdAndPostId(userId, postId).map(entity -> new Like(entity));
    }
}
