package com.example.backend.services.implementations;

import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Objects;

@Service
public class FileService {
    private final String uploadDir = FileSystems.getDefault().getPath("media").toAbsolutePath().toString();
    private final Path fileStorageLocation = Paths.get(uploadDir);

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Files.copy(file.getInputStream(), fileStorageLocation, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    public UrlResource getUrlResource(String filename) throws MalformedURLException {
        UrlResource resource = new UrlResource(this.fileStorageLocation.resolve(filename).normalize().toUri());
        if (resource.exists()){
            return resource;
        } else {
            throw new RuntimeException("File not found " + filename);
        }
    }

    public Boolean deleteFile(String filename){

        return true;
    }
}
