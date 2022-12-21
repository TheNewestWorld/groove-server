package org.bogus.groove.endpoint.inquiry;

import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.inquiry.InquiryGetResult;
import org.bogus.groove.domain.inquiryanswer.InquiryAnswer;

@RequiredArgsConstructor
public class InquiryGetResponse {
    private final Long id;

    private final Long userId;

    private final String title;

    private final String content;
    private final boolean isAnswer;
    private final InquiryAnswer inquiryAnswer;

    public InquiryGetResponse(InquiryGetResult inquiryGetResult) {
        this.id = inquiryGetResult.getId();
        this.userId = inquiryGetResult.getUserId();
        this.title = inquiryGetResult.getTitle();
        this.content = inquiryGetResult.getContent();
        this.isAnswer = inquiryGetResult.isAnswer();
        this.inquiryAnswer = inquiryGetResult.getInquiryAnswer();
    }


}
