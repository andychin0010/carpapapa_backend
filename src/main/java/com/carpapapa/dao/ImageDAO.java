package com.carpapapa.dao;

import com.carpapapa.domain.Image;
import com.carpapapa.domain.ImageType;

import java.util.List;

/**
 * Created by chishingchin on 3/10/16.
 */
public interface ImageDAO {

    void createProductImage(long productId, String path, ImageType type);

    void updateProductImage(long id, ImageType type, int order);

    void deleteProductImage(long id);

    void approveProductImage(long id);

    List<Image> getImagesByProductId(long productId, boolean approved);

    List<Image> getImagesByProductIdFilterType(long product, ImageType type, boolean approved);
}
