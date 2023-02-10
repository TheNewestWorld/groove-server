package org.bogus.groove.domain.inquiryanswer;

import java.time.LocalDateTime;
import lombok.Getter;
import org.bogus.groove.storage.entity.InquiryAnswerEntity;

@Getter
public class InquiryAnswer {

    private Long id;
    private LocalDateTime createAt;
    private String title;
    private String content;

    private Long refInquiryId;

    public InquiryAnswer(InquiryAnswerEntity inquiryAnswerEntity) {
        this.id = inquiryAnswerEntity.getId();
        this.createAt = inquiryAnswerEntity.getCreatedAt();
        this.title = inquiryAnswerEntity.getTitle();
        this.content = inquiryAnswerEntity.getContent();
        this.refInquiryId = inquiryAnswerEntity.getInquiryId();
    }
}
