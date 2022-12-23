package org.bogus.groove.endpoint.inquiry;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.inquiry.InquiryGetResult;
import org.bogus.groove.domain.inquiryanswer.InquiryAnswer;
import org.bogus.groove.object_storage.Attachment;

@RequiredArgsConstructor
public class InquiryGetResponse {
    private Long id;

    private Long userId;

    private String title;

    private String content;
    private boolean isAnswer;
    private InquiryAnswer inquiryAnswer;
    private List<Attachment> attachments;

    public InquiryGetResponse(InquiryGetResult inquiryGetResult) {
        this.id = inquiryGetResult.getId();
        this.userId = inquiryGetResult.getUserId();
        this.title = inquiryGetResult.getTitle();
        this.content = inquiryGetResult.getContent();
        this.isAnswer = inquiryGetResult.isAnswer();
        this.inquiryAnswer = inquiryGetResult.getInquiryAnswer();
        this.attachments = inquiryGetResult.getAttachments();
    }


}
