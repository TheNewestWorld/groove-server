package org.bogus.groove.domain.object;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.bogus.groove.common.ErrorType;
import org.bogus.groove.common.NotFoundException;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ObjectStorage {
    private final String objectDirectory = "upload";

    // TODO 아마도 S3 로 변경
    public ObjectUploadResult upload(MultipartFile record, AttachmentType attachmentType) {
        Path baseDirectory = Paths.get(new FileSystemResource("").getFile().getAbsolutePath()).resolve(objectDirectory);
        try {
            Files.createDirectories(baseDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String objectKey = UUID.randomUUID().toString();
        Path savePath = baseDirectory.resolve(objectKey);
        try {
            record.transferTo(savePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ObjectUploadResult(
            objectKey,
            savePath.toString(),
            record.getSize(),
            record.getOriginalFilename(),
            getExtension(record),
            attachmentType
        );
    }

    public FileInputStream download(String path) {
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new NotFoundException(ErrorType.NOT_FOUND_ATTACHMENT);
        }
    }

    private String getExtension(MultipartFile file) {
        return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }
}
