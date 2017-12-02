package com.carpapapa.domain;

import java.util.List;

/**
 * Created by chandler on 11/21/17.
 */
public class Products {
    List<Product> products;
    Integer count;

    public Products(List<Product> products, Integer count) {
        this.products = products;
        this.count = count;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
