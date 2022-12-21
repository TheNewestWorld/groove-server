package org.bogus.groove.domain.inquiry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.bogus.groove.domain.attachment.InquiryAttachmentCreateParam;
import org.bogus.groove.domain.inquiryanswer.InquiryAnswer;
import org.bogus.groove.domain.inquiryanswer.InquiryAnswerService;
import org.bogus.groove.storage.repository.InquiryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class InquiryServiceTest {

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private InquiryAnswerService inquiryAnswerService;
    @Autowired
    private InquiryRepository inquiryRepository;

    @Test
    public void test() {
        Long userId = 1L;
        String title = "제목";
        String content = "내용";
        List<InquiryAttachmentCreateParam> list = new ArrayList<>();
        Inquiry inquiry = inquiryService.create(userId, title, content, list);
        Long inquiryId = inquiry.getId();
        InquiryGetResult inquiryResponse = inquiryService.get(inquiryId);

        assertEquals(inquiryId, inquiryResponse.getId());
        assertEquals("제목", inquiryResponse.getTitle());
        assertEquals("내용", inquiryResponse.getContent());
        assertEquals(false, inquiryResponse.isAnswer());

        InquiryAnswer inquiryAnswerResponse = inquiryAnswerService.create(inquiryId, "답글제목", "답글내용");
        InquiryGetResult inquiryResponse1 = inquiryService.get(inquiryId);
        assertEquals(true, inquiryResponse1.isAnswer());
        assertEquals("답글내용", inquiryResponse1.getInquiryAnswer().getContent());
    }

    public void test2() {
    }

}