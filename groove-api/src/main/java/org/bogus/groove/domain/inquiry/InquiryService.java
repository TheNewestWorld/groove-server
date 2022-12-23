package org.bogus.groove.domain.inquiry;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.domain.attachment.InquiryAttachmentCreateParam;
import org.bogus.groove.domain.inquiryanswer.InquiryAnswerReader;
import org.bogus.groove.object_storage.Attachment;
import org.bogus.groove.object_storage.AttachmentDeleter;
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

    private final InquiryUpdater inquiryUpdater;

    private final InquiryDeleter inquiryDeleter;

    private final InquiryAnswerReader inquiryAnswerReader;

    private final AttachmentUploader attachmentUploader;

    private final AttachmentReader attachmentReader;

    private final AttachmentDeleter attachmentDeleter;


    public Inquiry create(Long userId, String title, String content, List<InquiryAttachmentCreateParam> inquiryAttachmentCreateParamList) {
        var inquiry = inquiryCreator.create(userId, title, content);

        inquiryAttachmentCreateParamList.stream().map(param -> attachmentUploader.upload(
            new AttachmentUploadParam(param.getInputStream(), param.getFileName(), param.getSize(), inquiry.getId(),
                param.getAttachmentType())));

        return inquiry;
    }

    public InquiryGetResult get(Long id) {
        var inquiry = inquiryReader.read(id);
        if (inquiry.isHasAnswer()) {
            var inquiryAnswer = inquiryAnswerReader.read(id);
            return new InquiryGetResult(inquiry, getAttachmentUri(inquiry), inquiryAnswer);
        }
        return new InquiryGetResult(inquiry, getAttachmentUri(inquiry), null);
    }

    public Slice<InquiryGetResult> getList(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var inquiries = inquiryReader.readAll(userId, pageable);
        List<InquiryGetResult> list = new ArrayList<>();
        inquiryReader.readAll(userId, pageable).stream()
            .forEach(inquiry -> {
                if (inquiry.isHasAnswer()) {
                    list.add(new InquiryGetResult(inquiry, getAttachmentUri(inquiry), inquiryAnswerReader.read(inquiry.getId())));
                } else {
                    list.add(new InquiryGetResult(inquiry, getAttachmentUri(inquiry), null));
                }
            });
        return new SliceImpl<>(list, inquiries.getPageable(), inquiries.hasNext());
    }

    public void update(Long id, Long userId, String title, String content,
                       List<InquiryAttachmentCreateParam> inquiryAttachmentCreateParamList) {

        var attachments = attachmentReader.readAll(id, AttachmentType.POST_IMAGE);
        attachments.addAll(attachmentReader.readAll(id, AttachmentType.POST_RECORD));
        attachments.forEach((attachment -> attachmentDeleter.delete(attachment.getId())));
        inquiryUpdater.update(id, userId, title, content);

        inquiryAttachmentCreateParamList.stream().map(param -> attachmentUploader.upload(
            new AttachmentUploadParam(param.getInputStream(), param.getFileName(), param.getSize(), id,
                param.getAttachmentType())));
    }

    public void delete(Long id) {
        inquiryDeleter.delete(id);
    }

    private List<Attachment> getAttachmentUri(Inquiry inquiry) {
        var attachments = attachmentReader.readAll(inquiry.getId(), AttachmentType.POST_IMAGE);
        attachments.addAll(attachmentReader.readAll(inquiry.getId(), AttachmentType.POST_RECORD));
        return attachments;
    }

}
