package org.bogus.groove.common;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonResponseError {
    private String code;
    private String message;
    private Map<String, Object> data;
}