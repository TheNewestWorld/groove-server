package org.bogus.groove.endpoint.inquiry;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.domain.inquiryanswer.InquiryAnswer;

@RequiredArgsConstructor
public class InquiryGetResponse {
    private final Long id;

    private final LocalDateTime createAt;

    private final String title;

    private final String content;
    private final boolean isAnswer;
    private final InquiryAnswer inquiryAnswer;


}
