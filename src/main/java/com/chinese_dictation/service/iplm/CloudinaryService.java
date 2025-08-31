package com.chinese_dictation.service.iplm;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public String uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "image",
                        "folder", "images",
                        "transformation", ObjectUtils.asMap(
                                "quality", "auto:good",
                                "fetch_format", "auto"
                        )
                ));
        return uploadResult.get("secure_url").toString();
    }

    public String uploadAudio(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "video",
                        "folder", "audios",
                        "format", "mp3"
                ));
        return uploadResult.get("secure_url").toString();
    }

    public boolean deleteFile(String publicId, String resourceType) {
        try {
            Map result = cloudinary.uploader().destroy(publicId,
                    ObjectUtils.asMap("resource_type", resourceType.equals("image") ? "image" : "video"));
            return "ok".equals(result.get("result"));
        } catch (IOException e) {
            return false;
        }
    }
}
