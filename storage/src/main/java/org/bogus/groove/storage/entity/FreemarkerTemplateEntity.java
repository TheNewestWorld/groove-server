package org.bogus.groove.storage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bogus.groove.common.enumeration.FreemarkerTemplateType;

@Entity
@Getter
@Table(name = "freemarker_template")
@NoArgsConstructor
public class FreemarkerTemplateEntity extends BaseEntity {

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private FreemarkerTemplateType type;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "delete_flag")
    private boolean deleteFlag;

    public FreemarkerTemplateEntity(FreemarkerTemplateType type, String name, String title, String content) {
        super();
        this.type = type;
        this.name = name;
        this.title = title;
        this.content = content;
    }
}
