package com.carpapapa.service;

import com.carpapapa.dao.ImageDAO;
import com.carpapapa.domain.Image;
import com.carpapapa.domain.ImageType;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by chishingchin on 3/10/16.
 */

@Service
public class DefaultImageService implements ImageService {

    @Value("${product.image.path}")
    private String imagePath;

    @Autowired
    private ImageDAO imageDAO;

    private int DEFAULT_WIDTH = 553;
    private int DEFAULT_HEIGHT = 484;
    private int ICON_WIDTH = 390;
    private int ICON_HEIGHT = 356;

    @Override
    public void createProductImage(long productId, MultipartFile file, ImageType type) {
        int targetWidth;
        int targetHeight;
        double scale;

        String fileName = UUID.randomUUID().toString();
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        if (!file.isEmpty()) {
            try {
                BufferedImage img = ImageIO.read(file.getInputStream());
                BufferedImage result = scaleImage(img, type);
//                int inputWidth = img.getWidth();
//                int inputHeight = img.getHeight();
//
//                if (type.equals(ImageType.DEFAULT)) {
//                    targetWidth = (int) (this.DEFAULT_WIDTH * 1.5);
//                    targetHeight = (int) (this.DEFAULT_HEIGHT * 1.5);
//                } else {
//                    return;
//                }
//
//                if (inputWidth <= inputHeight) {
//                    scale = (double) targetWidth / (double) inputWidth;
//                } else {
//                    scale = (double) targetHeight / (double) inputHeight;
//                }
//
//                BufferedImage buffered = Thumbnails.of(img).scale(scale).asBufferedImage();


                ImageIO.write(result, "jpg", new File(this.imagePath + fileName + extension));
//                ImageIO.write(buffered, "jpg", new File("/Users/chandler/Documents/Carpapapa/repo/images/" + fileName + extension));
//                ImageIO.write(buffered, "jpg", new File("/Users/chandler/Documents/test" + extension));


//  This code reduce the image quality
//                OutputStream os =new FileOutputStream(new File(this.imagePath + fileName + extension));
//
//                Iterator<ImageWriter> writers =  ImageIO.getImageWritersByFormatName("jpg");
//                ImageWriter writer = writers.next();
//
//                ImageOutputStream ios = ImageIO.createImageOutputStream(os);
//                writer.setOutput(ios);
//
//                ImageWriteParam param = writer.getDefaultWriteParam();
////                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
////                param.setCompressionQuality(0.5f);
//                writer.write(null, new IIOImage(buffered, null, null), param);
//
//                os.close();
//                ios.close();
//                writer.dispose();

                imageDAO.createProductImage(productId, "images/" + fileName + extension, type);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void overwriteProductImage(long id, String path, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String[] paths = path.split("/");
                if (paths.length > 0) {
                    BufferedImage img = ImageIO.read(file.getInputStream());

                    BufferedImage newBufferedImage = new BufferedImage(img.getWidth(),
                            img.getHeight(), BufferedImage.TYPE_INT_RGB);
                    newBufferedImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);

                    // write to jpeg file
                    ImageIO.write(newBufferedImage, "jpg", new File(this.imagePath + paths[paths.length - 1]));

                    imageDAO.approveProductImage(id);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public void updateProductImage(long id, ImageType type, int order) {
        imageDAO.updateProductImage(id, type, order);
    }

    @Override
    public void deleteProductImage(long id) {
        imageDAO.deleteProductImage(id);
    }

    @Override
    public List<Image> getImagesByProductId(long productId, boolean approved) {
        return imageDAO.getImagesByProductId(productId, approved);
    }

    @Override
    public List<Image> updateProductImages(long productId, List<Image> images) {
        for (Image image : images) {
            imageDAO.updateProductImage(image.getId(), image.getType(), image.getOrder());
        }
        return getImagesByProductId(productId, false);
    }

    private BufferedImage scaleImage(BufferedImage img, ImageType type) {
        int inputWidth;
        int inputHeight;
        int targetWidth;
        int targetHeight;
        double scale;
        BufferedImage result = null;

        inputWidth = img.getWidth();
        inputHeight = img.getHeight();

        if (type.equals(ImageType.DEFAULT)) {
            targetWidth = (int) (DEFAULT_WIDTH * 1.5);
            targetHeight = (int) (DEFAULT_HEIGHT * 1.5);

            if (((targetWidth - 5) < inputWidth) && (inputWidth < (targetWidth + 5)) &&
                    ((targetHeight - 5 < inputHeight) && (inputHeight < (targetHeight + 5)))) {
                return img;
            }
        } else if (type.equals(ImageType.ICON))  {
            targetWidth = (int) (ICON_WIDTH * 1.5);
            targetHeight = (int) (ICON_HEIGHT * 1.5);

            if (((targetWidth -5) < inputWidth) && (inputWidth < (targetWidth + 5)) &&
                    ((targetHeight - 5 < inputHeight) && (inputHeight < (targetHeight + 5)))) {
                return img;
            }
        } else {
            return img;
        }

        if (inputWidth <= inputHeight) {
            scale = (double) targetWidth / (double) inputWidth;
        } else {
            scale = (double) targetHeight / (double) inputHeight;
        }

        try {
            result = Thumbnails.of(img).scale(scale).asBufferedImage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
