package org.bogus.groove.endpoint.inquiry;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InquiryCreateRequest {
    private final String title;

    private final String content;

}
