package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class InquiryAnswerEntity extends BaseEntity {

    @Column(name = "ref_inquiry_id")
    private Long inquiryId;
    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;


    public InquiryAnswerEntity(Long inquiryId, String title, String content) {
        this.inquiryId = inquiryId;
        this.title = title;
        this.content = content;
    }

}
