package org.bogus.groove.domain.coaching;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.storage.repository.CoachingRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoachingUpdater {
    private final CoachingRepository coachingRepository;

    @Transactional
    public void updateCoaching(Long coachingId, String title, String content, String voiceFileMap, String imageFileMap,
                               boolean publicFlag) {
        var entity = coachingRepository.findById(coachingId)
            .orElseThrow(() -> new org.bogus.groove.common.NotFoundException(org.bogus.groove.common.ErrorType.NOT_FOUNT_COACHING_INFO));
        entity.setTitle(title);
        entity.setContent(content);
        entity.setVoiceFileMap(voiceFileMap);
        entity.setImageFileMap(imageFileMap);
        entity.setPublicFlag(publicFlag);
    }
}
