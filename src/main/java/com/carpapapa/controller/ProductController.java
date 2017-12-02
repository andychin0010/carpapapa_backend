package com.carpapapa.controller;

import com.carpapapa.domain.Product;
import com.carpapapa.domain.ProductOption;
import com.carpapapa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by chishingchin on 3/2/16.
 */

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity<?> getProducts(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                  @RequestParam(value = "limit", required = false, defaultValue = "12") int limit,
                                  @RequestParam(value = "all", defaultValue = "false") boolean all,
                                  @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                  @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
                                  @RequestParam(value = "make", required = false) String make,
                                  @RequestParam(value = "exColor", required = false) String exColor,
                                  @RequestParam(value = "status", required = false) String status,
                                  @RequestParam(value = "soldAfter", required = false) Long soldAfter,
                                  @RequestParam(value = "soldBefore", required = false) Long soldBefore) {

        return new ResponseEntity<>(productService.getProducts(offset, limit, all, minPrice, maxPrice, make, exColor, status, soldAfter, soldBefore), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<?> getProduct(@PathVariable long id,
                                 @RequestParam(value = "approvedImages", defaultValue = "true") boolean approvedImages) {
        Product product = productService.getProductById(id, approvedImages);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product Not Found.", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    ResponseEntity<?> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    ResponseEntity<?> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}/options", method = RequestMethod.GET)
    ResponseEntity<?> getProductOptions(@PathVariable long id) {
        return new ResponseEntity<>(productService.getProductOptionsById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}/options/ensure", method = RequestMethod.POST)
    ResponseEntity<?> ensureProductOption(@PathVariable long id,
                                          @RequestBody List<ProductOption> options) {
        return new ResponseEntity<>(productService.ensureProductOptions(id, options), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    ResponseEntity<?> searchProducts(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                  @RequestParam(value = "limit", required = false, defaultValue = "12") int limit,
                                  @RequestParam(value = "state", required = false)  Boolean state,
                                  @RequestParam(value = "make", required = false) String make,
                                  @RequestParam(value = "model", required = false) String model,
                                  @RequestParam(value = "exColor", required = false) String exColor,
                                  @RequestParam(value = "year", required = false) Integer year,
                                  @RequestParam(value = "status", required = false) String status) {
        return new ResponseEntity<>(productService.searchProducts(offset, limit, state, make, model, exColor, year, status), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    ResponseEntity<?> deleteProductById(@PathVariable long id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
