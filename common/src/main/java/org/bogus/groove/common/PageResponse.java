package org.bogus.groove.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PageResponse<T> {
    private final int page;
    private final int size;
    private final T contents;
    private final boolean hasNext;
}
