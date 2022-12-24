package org.bogus.groove.common.enumeration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReportReasonType {
    HARSH_PROFANITY("거친 욕설을 사용했어요"),
    FALSE_INFORMATION("거짓된 정보를 담고있어요"),
    INAPPROPRIATE_CONTENT("부적절한 내용을 담고있어요");

    private final String message;

}