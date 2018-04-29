package com.carpapapa.service;

import com.carpapapa.dao.ImageDAO;
import com.carpapapa.dao.ProductDAO;
import com.carpapapa.domain.ImageType;
import com.carpapapa.domain.Product;
import com.carpapapa.domain.ProductOption;
import com.carpapapa.domain.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chishingchin on 3/3/16.
 */

@Service
public class DefaultProductService implements ProductService {

    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ImageDAO imageDao;

    @Override
    public Products getProducts(int offset, int limit, boolean all, Integer minPrice, Integer maxPrice, String make, String exColor, String status, Long soldAfter, Long soldBefore) {
        Products products = productDAO.getProducts(offset, limit, all, minPrice, maxPrice, make, exColor, status, soldAfter, soldBefore);
        for (Product product : products.getProducts()) {
            product.setImages(imageDao.getImagesByProductIdFilterType(product.getId(), ImageType.ICON, true));
        }

        return products;
    }

    @Override
    public Product getProductById(long id, boolean approvedImages) {

        Product product = productDAO.getProductById(id);
        if (product != null) {
            product.setImages(imageDao.getImagesByProductId(id, approvedImages));
            product.setOptions(getProductOptionsById(id));
        }

        return product;
    }

    @Override
    public Product createProduct(Product product) {
        return productDAO.createProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    @Override
    public List<ProductOption> getProductOptionsById(long id) {
        return productDAO.getProductOptionsById(id);
    }

    @Override
    public List<ProductOption> ensureProductOptions(long id, List<ProductOption> options) {
        productDAO.ensureProductOptions(id, options);
        return productDAO.getProductOptionsById(id);
    }

    @Override
    public Products searchProducts(int offset, int limit, Boolean state, String make, String model, String exColor, Integer year, String status, String vin) {
        Products products = productDAO.searchProducts(offset, limit, state, make, model, exColor, year, status, vin);
        for (Product product : products.getProducts()) {
            product.setImages(imageDao.getImagesByProductIdFilterType(product.getId(), ImageType.ICON, true));
        }

        return products;
    }

    @Override
    public void deleteProductById(long id) {
        productDAO.deleteProductById(id);
    }

    @Override
    public List<String> getMakes() {
        return productDAO.getMakes();
    }

    @Override
    public List<String> getColors() {
        return productDAO.getColors();
    }
}
