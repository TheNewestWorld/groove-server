package org.bogus.groove.object_storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.NotFoundException;
import org.springframework.core.io.FileSystemResource;

class FileSystemBasedObjectStorage implements ObjectStorage {
    @Override
    public ObjectUploadResult upload(InputStream inputStream, AttachmentType attachmentType) {
        Path baseDirectory = getBaseDirectoryPath(attachmentType);

        try {
            Files.createDirectories(baseDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String objectKey = UUID.randomUUID().toString();
        File file = baseDirectory.resolve(objectKey).toFile();

        try (var output = new FileOutputStream(file)) {
            output.write(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ObjectUploadResult(
            objectKey,
            file.getAbsolutePath()
        );
    }

    @Override
    public InputStream download(String objectKey, AttachmentType attachmentType) {
        Path filePath = getBaseDirectoryPath(attachmentType).resolve(objectKey);

        try {
            return new FileInputStream(filePath.toString());
        } catch (FileNotFoundException e) {
            throw new NotFoundException(ErrorType.NOT_FOUND_ATTACHMENT);
        }
    }

    private static Path getBaseDirectoryPath(AttachmentType attachmentType) {
        return Paths.get(new FileSystemResource("").getFile().getAbsolutePath())
            .resolve("upload")
            .resolve(attachmentType.getPath());
    }
}
