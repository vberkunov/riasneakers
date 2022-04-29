package com.shopservice.riasneakers.services.serviceImpl;

import com.shopservice.riasneakers.dto.response.UploadPhotoResponse;
import com.shopservice.riasneakers.entity.Photo;
import com.shopservice.riasneakers.repository.PhotoRepository;
import com.shopservice.riasneakers.services.PhotoStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
public class PhotoStorageServiceImpl implements PhotoStorageService {

    @Autowired
    private PhotoRepository photoRepository;

    private final Logger logger = LoggerFactory.getLogger(PhotoStorageServiceImpl.class.getSimpleName());

    public UploadPhotoResponse store(MultipartFile file) {

        String status = "";
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            logger.info("Start resizing photo"+ fileName +"with content type:" + file.getContentType().substring(6,10));




            Photo photo = new Photo(fileName, file.getContentType(), compressImage(file));
            photoRepository.save(photo);
            logger.info("Finish resizing photo");

            status = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/photo/")
                    .path(photo.getId())
                    .toUriString();
            return new UploadPhotoResponse(photo.getId(), status);
        } catch (MaxUploadSizeExceededException e) {
            logger.info("Error resizing photo"+ e.getMessage());
            status = "Failed. File is too large";
            return new UploadPhotoResponse("",status);
        }
    }

    public Photo getFile(String id) {
        return photoRepository.findById(id).get();
    }

    private byte[] compressImage(MultipartFile mpFile) {
        float quality = 0.1f;
        String imageName = mpFile.getOriginalFilename();
        String imageExtension = imageName.substring(imageName.lastIndexOf(".") + 1);
        // Returns an Iterator containing all currently registered ImageWriters that claim to be able to encode the named format.
        // You don't have to register one yourself; some are provided.
        ImageWriter imageWriter = ImageIO.getImageWritersByFormatName(imageExtension).next();
        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // Check the api value that suites your needs.
        // A compression quality setting of 0.0 is most generically interpreted as "high compression is important,"
        // while a setting of 1.0 is most generically interpreted as "high image quality is important."
        imageWriteParam.setCompressionQuality(quality);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // MemoryCacheImageOutputStream: An implementation of ImageOutputStream that writes its output to a regular
        // OutputStream, i.e. the ByteArrayOutputStream.
        ImageOutputStream imageOutputStream = new MemoryCacheImageOutputStream(baos);
        // Sets the destination to the given ImageOutputStream or other Object.
        imageWriter.setOutput(imageOutputStream);
        BufferedImage originalImage = null;
        try (InputStream inputStream = mpFile.getInputStream()) {
            originalImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            String info = String.format("compressImage - bufferedImage (file %s)- IOException - message: %s ", imageName, e.getMessage());
            logger.error(info);
            return baos.toByteArray();
        }
        IIOImage image = new IIOImage(originalImage, null, null);
        try {
            imageWriter.write(null, image, imageWriteParam);
        } catch (IOException e) {
            String info = String.format("compressImage - imageWriter (file %s)- IOException - message: %s ", imageName, e.getMessage());
            logger.error(info);
        } finally {
            imageWriter.dispose();
        }
        return baos.toByteArray();
    }


}
