package com.carpapapa.service;

import com.carpapapa.domain.Image;
import com.carpapapa.domain.ImageType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by chishingchin on 3/10/16.
 */
public interface ImageService {

    void createProductImage(long productId, MultipartFile file, ImageType type);

    void overwriteProductImage(long id, String path, MultipartFile file);

    void updateProductImage(long id, ImageType type, int order);

    void deleteProductImage(long id);

    List<Image> getImagesByProductId(long productId, boolean approved);

    List<Image> updateProductImages(long productId, List<Image> images);
}
