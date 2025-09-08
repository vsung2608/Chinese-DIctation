package com.chinese_dictation.service.iplm;

import com.chinese_dictation.model.dto.response.FileUploadResponse;
import com.chinese_dictation.service.ICloudinaryService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService implements ICloudinaryService {
    private final Cloudinary cloudinary;

    public FileUploadResponse uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "image",
                        "folder", "images",
                        "transformation", ObjectUtils.asMap(
                                "quality", "auto:good",
                                "fetch_format", "auto"
                        )
                ));
        return new FileUploadResponse(
                uploadResult.get("secure_url").toString(),
                uploadResult.get("public_id").toString()
        );
    }

    public FileUploadResponse uploadAudio(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "video",
                        "folder", "audios",
                        "format", "mp3"
                ));
        return new FileUploadResponse(
                uploadResult.get("secure_url").toString(),
                uploadResult.get("public_id").toString()
        );
    }

    public void deleteFile(String publicId, String resourceType) throws IOException {
        Map deleteResult = cloudinary.uploader().destroy(publicId,
                ObjectUtils.asMap("resource_type", resourceType));

        String result = deleteResult.get("result").toString();
        if (!"ok".equals(result)) {
            throw new IOException("Failed to delete file: " + result);
        }
    }
}
