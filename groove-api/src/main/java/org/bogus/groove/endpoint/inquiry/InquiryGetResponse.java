package org.bogus.groove.endpoint.inquiry;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bogus.groove.domain.inquiry.InquiryGetResult;
import org.bogus.groove.domain.inquiryanswer.InquiryAnswer;
import org.bogus.groove.object_storage.Attachment;

@Getter
@AllArgsConstructor
public class InquiryGetResponse {
    private Long id;
    private LocalDateTime createAt;

    private Long userId;

    private String title;

    private String content;
    private boolean hasAnswer;
    private InquiryAnswer inquiryAnswer;
    private List<Attachment> attachments;

    public InquiryGetResponse(InquiryGetResult inquiryGetResult) {
        this.id = inquiryGetResult.getId();
        this.createAt = inquiryGetResult.getCreateAt();
        this.userId = inquiryGetResult.getUserId();
        this.title = inquiryGetResult.getTitle();
        this.content = inquiryGetResult.getContent();
        this.hasAnswer = inquiryGetResult.isHasAnswer();
        this.inquiryAnswer = inquiryGetResult.getInquiryAnswer();
        this.attachments = inquiryGetResult.getAttachments();
    }
}
