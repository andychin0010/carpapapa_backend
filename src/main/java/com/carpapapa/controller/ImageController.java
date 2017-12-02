package com.carpapapa.controller;

import com.carpapapa.domain.Image;
import com.carpapapa.domain.ImageType;
import com.carpapapa.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by chishingchin on 3/5/16.
 */

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @CrossOrigin
    @RequestMapping(value = "types/{type}/products/{product_id}/create", method = RequestMethod.POST)
    public ResponseEntity<?> createImage(@PathVariable(value = "type") ImageType type,
                                         @PathVariable(value = "product_id") long productId,
                                         @RequestParam(value = "file", required = true) MultipartFile file) {
        imageService.createProductImage(productId, file, type);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "{id}/overwrite", method = RequestMethod.POST)
    public ResponseEntity<?> overwriteImage(@PathVariable(value = "id") long id,
                                            @RequestParam(value = "path") String path,
                                            @RequestParam(value = "file", required = true) MultipartFile file) {
        imageService.overwriteProductImage(id, path, file);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateImage(@PathVariable(value = "id") long id,
                                         @RequestParam(value = "type") ImageType type,
                                         @RequestParam(value = "order") int order) {
        imageService.updateProductImage(id, type, order);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
    public ResponseEntity<?> deleteImage(@PathVariable(value = "id") long id) {
        imageService.deleteProductImage(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "products/{product_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getImagesByProductId(@PathVariable(value = "product_id") long productId,
                                                  @RequestParam(value = "approved", defaultValue = "true") boolean approved) {
        return new ResponseEntity<Object>(imageService.getImagesByProductId(productId, approved), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "products/{product_id}/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateImages(@PathVariable(value = "product_id") long productId,
                                          @RequestBody List<Image> images) {
        return new ResponseEntity<>(imageService.updateProductImages(productId, images), HttpStatus.OK);
    }
}
