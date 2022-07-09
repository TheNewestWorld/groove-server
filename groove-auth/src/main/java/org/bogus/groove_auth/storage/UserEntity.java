package org.bogus.groove_auth.storage;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bogus.groove.common.enumeration.UserType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Table(
    name = "`user`",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "type"})}
)
@Entity
@Getter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Column(name = "email")
    private String email;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(name = "reg_ts", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "upd_ts", insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UserEntity(String email, UserType type) {
        this.email = email;
        this.type = type;
    }
}
