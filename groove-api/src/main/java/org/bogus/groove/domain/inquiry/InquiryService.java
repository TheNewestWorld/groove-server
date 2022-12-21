package org.bogus.groove.domain.inquiry;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.domain.attachment.InquiryAttachmentCreateParam;
import org.bogus.groove.domain.inquiryanswer.InquiryAnswerReader;
import org.bogus.groove.object_storage.Attachment;
import org.bogus.groove.object_storage.AttachmentReader;
import org.bogus.groove.object_storage.AttachmentUploadParam;
import org.bogus.groove.object_storage.AttachmentUploader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryCreator inquiryCreator;

    private final InquiryReader inquiryReader;

    private final InquiryAnswerReader inquiryAnswerReader;

    private final AttachmentUploader attachmentUploader;

    private final AttachmentReader attachmentReader;


    public Inquiry create(Long userId, String title, String content, List<InquiryAttachmentCreateParam> inquiryAttachmentCreateParams) {
        var inquiry = inquiryCreator.create(userId, title, content);

        inquiryAttachmentCreateParams.stream().map(param -> attachmentUploader.upload(
            new AttachmentUploadParam(param.getInputStream(), param.getFileName(), param.getSize(), inquiry.getId(),
                param.getAttachmentType())));

        return inquiry;
    }

    public InquiryGetResult get(Long id) {
        var inquiry = inquiryReader.read(id);
        if (inquiry.isAnswer()) {
            var inquiryAnswer = inquiryAnswerReader.read(inquiry.getAnswerId());
            return new InquiryGetResult(inquiry, getAttachmentUri(inquiry), inquiryAnswer);
        }
        return new InquiryGetResult(inquiry, getAttachmentUri(inquiry), null);

    }

    public Slice<InquiryGetResult> getList(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var inquiries = inquiryReader.readAll(userId, pageable);
        return new SliceImpl<>(inquiryReader.readAll(userId, pageable).stream()
            .map(inquiry -> new InquiryGetResult(inquiry, getAttachmentUri(inquiry), inquiryAnswerReader.read(inquiry.getAnswerId())))
            .toList(), inquiries.getPageable(), inquiries.hasNext());
    }

    private List<Attachment> getAttachmentUri(Inquiry inquiry) {
        var attachments = attachmentReader.readAll(inquiry.getId(), AttachmentType.POST_IMAGE);
        attachments.addAll(attachmentReader.readAll(inquiry.getId(), AttachmentType.POST_RECORD));
        return attachments;
    }

}
