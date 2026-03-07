package com.example.Backend_hotels.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.Backend_hotels.exception.ImageUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageService {

    private final Cloudinary cloudinary;

    public ImageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file, String folder) {

        validateFile(file);

        try {
            Map<?, ?> uploadResult = cloudinary.uploader()
                    .upload(file.getBytes(),
                            ObjectUtils.asMap("folder", folder));

            return uploadResult.get("secure_url").toString();

        } catch (IOException e) {
            throw new ImageUploadException(
                    "Erro ao enviar imagem para Cloudinary",
                    e
            );
        }
    }

    private void validateFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new ImageUploadException("Arquivo inválido ou vazio", null);
        }

        if (!file.getContentType().startsWith("image/")) {
            throw new ImageUploadException("Apenas imagens são permitidas", null);
        }
    }
}