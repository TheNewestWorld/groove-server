package org.bogus.groove.object_storage;

import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.GetNamespaceRequest;
import com.oracle.bmc.objectstorage.requests.GetObjectRequest;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import com.oracle.bmc.objectstorage.transfer.UploadConfiguration;
import com.oracle.bmc.objectstorage.transfer.UploadManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import org.bogus.groove.common.enumeration.AttachmentType;
import org.bogus.groove.common.exception.ErrorType;
import org.bogus.groove.common.exception.InternalServerException;

class OciBucketBasedObjectStorage implements ObjectStorage {
    private final ObjectStorageClient objectStorageClient;
    private final OciProperty ociProperty;
    private final String namespace;

    public OciBucketBasedObjectStorage(ObjectStorageClient objectStorageClient, OciProperty ociProperty) {
        this.objectStorageClient = objectStorageClient;
        this.ociProperty = ociProperty;
        this.namespace = objectStorageClient.getNamespace(GetNamespaceRequest.builder().build()).getValue();
    }

    // TODO batchUpload, batchRead, delete, update ...
    @Override
    public ObjectUploadResult upload(InputStream inputStream, AttachmentType attachmentType) {
        var uploadConfiguration = UploadConfiguration.builder()
                .allowMultipartUploads(true)
                .allowParallelUploads(true)
                .build();

        var uploadManager = new UploadManager(objectStorageClient, uploadConfiguration);

        String objectKey = UUID.randomUUID().toString();
        PutObjectRequest request =
            PutObjectRequest.builder()
                .bucketName(ociProperty.getBucketName())
                .namespaceName(namespace)
                .objectName(getFilePath(objectKey, attachmentType))
                .build();


        try {
            var uploadDetails = UploadManager.UploadRequest.builder(inputStream, inputStream.available())
                .allowOverwrite(true)
                .build(request);
            uploadManager.upload(uploadDetails);
        } catch (IOException e) {
            throw new InternalServerException(ErrorType.FILE_UPLOAD);
        }

        return new ObjectUploadResult(
            objectKey,
            getFilePath(objectKey, attachmentType)
        );
    }

    @Override
    public InputStream download(String objectKey, AttachmentType attachmentType) {
        var object = objectStorageClient.getObject(
            GetObjectRequest.builder()
                .namespaceName(namespace)
                .bucketName(ociProperty.getBucketName())
                .objectName(String.join("/", "upload", attachmentType.getPath(), objectKey))
                .build()
        );
        return object.getInputStream();
    }

    private String getFilePath(String objectKey, AttachmentType attachmentType) {
        return String.join("/",
            attachmentType.isPreAuthorized() ? "upload/public" : "upload",
            attachmentType.getPath(),
            objectKey
        );
    }
}
