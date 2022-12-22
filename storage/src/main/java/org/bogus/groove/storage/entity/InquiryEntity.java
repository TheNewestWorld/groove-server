package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class InquiryEntity extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "answer_flag")
    private boolean isAnswer;

    @Column(name = "ref_answer_id")
    private Long answerId;

    public InquiryEntity(Long userId, String title, String content) {
        super();
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public InquiryEntity(Long userId, boolean isAnswer, Long answerId) {
        this();
        this.userId = userId;
        this.isAnswer = isAnswer;
        this.answerId = answerId;
    }


}
