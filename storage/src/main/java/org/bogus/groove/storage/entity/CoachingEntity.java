package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coaching")
@Getter
@NoArgsConstructor
public class CoachingEntity extends BaseEntity {
    @Column(name = "refTrainerId")
    private Long refTrainerId = 0L;

    public CoachingEntity(Long refTrainerId) {
        super();
        this.refTrainerId = refTrainerId;
    }
}

