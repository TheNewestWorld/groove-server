package org.bogus.groove.domain.object;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ObjectStorage {
    private final String objectDirectory = "test";

    // TODO 아마도 S3 로 변경
    public ObjectUploadResult upload(MultipartFile record, AttachmentType attachmentType) throws IOException {
        Path baseDirectory = Paths.get(new FileSystemResource("").getFile().getAbsolutePath()).resolve(objectDirectory);
        Files.createDirectories(baseDirectory);

        String objectKey = UUID.randomUUID().toString();
        Path savePath = baseDirectory.resolve(objectKey);
        record.transferTo(savePath);

        return new ObjectUploadResult(
            objectKey,
            savePath.toString(),
            record.getSize(),
            record.getOriginalFilename(),
            getExtension(record),
            attachmentType
        );
    }

    private String getExtension(MultipartFile file) {
        return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }
}
