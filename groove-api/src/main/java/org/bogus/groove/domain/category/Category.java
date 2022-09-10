package org.bogus.groove.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Category {
    private Long id;

    private String name;

    private Long categoryGroupId;
}
