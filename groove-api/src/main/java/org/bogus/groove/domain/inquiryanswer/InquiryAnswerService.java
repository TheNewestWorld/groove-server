package org.bogus.groove.domain.inquiryanswer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryAnswerService {
    private final InquiryAnswerCreator inquiryAnswerCreator;

    public InquiryAnswer create(Long refInquiryId, Long userId, String title, String content) {
        InquiryAnswer inquiryAnswer = inquiryAnswerCreator.create(refInquiryId, userId, title, content);
        return inquiryAnswer;
    }


}
