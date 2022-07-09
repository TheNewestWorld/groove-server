package org.bogus.groove.domain.something;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Something {
    private Long id;
    private String name;
    private Integer age;
}
