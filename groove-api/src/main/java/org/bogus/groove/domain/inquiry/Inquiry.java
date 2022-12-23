package org.bogus.groove.domain.inquiry;

import java.time.LocalDateTime;
import lombok.Getter;
import org.bogus.groove.storage.entity.InquiryEntity;

@Getter
public class Inquiry {

    private Long id;
    private LocalDateTime createAt;
    private Long userId;
    private String title;
    private String content;

    private boolean hasAnswer;

    private Long answerId;


    public Inquiry(InquiryEntity inquiryEntity) {
        this.id = inquiryEntity.getId();
        this.createAt = inquiryEntity.getCreatedAt();
        this.userId = inquiryEntity.getUserId();
        this.title = inquiryEntity.getTitle();
        this.content = inquiryEntity.getContent();
        this.hasAnswer = inquiryEntity.isHasAnswer();
        this.answerId = inquiryEntity.getAnswerId();
    }


}
