package org.bogus.groove.domain.voc;

import java.time.LocalDateTime;
import lombok.Getter;
import org.bogus.groove.storage.entity.VocEntity;

@Getter
public class Voc {
    private Long id;
    private LocalDateTime creatAt;
    private Long userId;
    private String content;

    public Voc(VocEntity vocEntity) {
        this.id = vocEntity.getId();
        this.creatAt = vocEntity.getCreatedAt();
        this.userId = vocEntity.getUserId();
        this.content = vocEntity.getContent();

    }
}
