package org.bogus.groove.endpoint.coaching;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.CommonResponse;
import org.bogus.groove.domain.coaching.CoachingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/coaching")
@RestController
@RequiredArgsConstructor
public class CoachingController {
    private final CoachingService coachingService;

    @PostMapping("/enroll")
    public CommonResponse<Void> createCoaching(@RequestBody CoachingCreateRequest request) {
        coachingService.createCoaching(request.getRefTrainerId(), request.getRefUserId(), request.getTitle(),
            request.getContent(), request.getVoiceFileMap(), request.getImageFileMap(), request.isPublicFlag(), request.getCoachingState());
        return CommonResponse.success();
    }
}