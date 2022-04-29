package com.shopservice.riasneakers.controllers;

import com.shopservice.riasneakers.dto.response.UploadPhotoResponse;
import com.shopservice.riasneakers.entity.Photo;
import com.shopservice.riasneakers.services.serviceImpl.PhotoStorageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PhotoController {

    private final Logger logger = LoggerFactory.getLogger(PhotoController.class.getSimpleName());
    private final PhotoStorageServiceImpl storageService;

    public PhotoController(PhotoStorageServiceImpl storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/photo")
    public UploadPhotoResponse uploadPhoto(@RequestParam("file") MultipartFile file) {
        logger.info("Storing photo to db");
        return storageService.store(file);

    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        logger.info("Getting photo from db");
        Photo photo = storageService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photo.getName() + "\"")
                .body(photo.getData());
    }
}
