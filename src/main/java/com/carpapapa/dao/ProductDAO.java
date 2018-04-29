package com.carpapapa.dao;

import com.carpapapa.domain.Product;
import com.carpapapa.domain.ProductOption;
import com.carpapapa.domain.Products;

import java.util.List;

/**
 * Created by chishingchin on 3/3/16.
 */
public interface ProductDAO {

    Products getProducts(int offset, int limit, boolean all, Integer minPrice, Integer maxPrice, String make, String exColor, String status, Long soldAfter, Long soldBefore);

    Product getProductById(long id);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    List<ProductOption> getProductOptionsById(long id);

    void ensureProductOptions(long id, List<ProductOption> options);

    Products searchProducts(int offset, int limit, Boolean state, String make, String model, String exColor, Integer year, String status, String vin);

    void deleteProductById(long id);

    List<String> getMakes();

    List<String> getColors();
}
