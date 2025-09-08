package com.chinese_dictation.service;

import com.chinese_dictation.model.dto.response.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ICloudinaryService {
    FileUploadResponse uploadImage(MultipartFile file) throws IOException;
    FileUploadResponse uploadAudio(MultipartFile file) throws IOException;
    void deleteFile(String publicId, String resourceType) throws IOException;
}
