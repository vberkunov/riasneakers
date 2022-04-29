package com.shopservice.riasneakers.services;

import com.shopservice.riasneakers.dto.response.UploadPhotoResponse;
import com.shopservice.riasneakers.entity.Photo;
import org.springframework.web.multipart.MultipartFile;


public interface PhotoStorageService {

     UploadPhotoResponse store(MultipartFile file);

     Photo getFile(String id);
}
