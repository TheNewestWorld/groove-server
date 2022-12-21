package org.bogus.groove.domain.voc;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class VocGetResult {
    private Long id;
    private LocalDateTime creatAt;
    private Long userId;
    private String content;

    public VocGetResult(Voc voc) {
        this.id = voc.getId();
        this.creatAt = voc.getCreatAt();
        this.userId = voc.getUserId();
        this.content = voc.getContent();


    }
}
