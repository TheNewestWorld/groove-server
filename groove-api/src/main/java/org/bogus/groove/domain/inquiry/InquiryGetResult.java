package org.bogus.groove.domain.inquiry;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import org.bogus.groove.domain.inquiryanswer.InquiryAnswer;
import org.bogus.groove.object_storage.Attachment;

@Getter
public class InquiryGetResult {
    private Long id;
    private LocalDateTime createAt;
    private Long userId;
    private String title;
    private String content;
    private boolean isAnswer;
    private List<Attachment> attachments;
    private InquiryAnswer inquiryAnswer;

    public InquiryGetResult(Inquiry inquiry, List<Attachment> attachments, InquiryAnswer inquiryAnswer) {
        this.id = inquiry.getId();
        this.createAt = inquiry.getCreateAt();
        this.userId = inquiry.getUserId();
        this.title = inquiry.getTitle();
        this.content = inquiry.getContent();
        this.isAnswer = inquiry.isAnswer();
        this.attachments = getAttachments();
        this.inquiryAnswer = inquiryAnswer;
    }
}
