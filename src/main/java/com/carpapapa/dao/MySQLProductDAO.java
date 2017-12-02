package com.carpapapa.dao;

import com.carpapapa.domain.Product;
import com.carpapapa.domain.ProductOption;
import com.carpapapa.domain.ProductStatus;
import com.carpapapa.domain.Products;
import com.carpapapa.mapper.ProductOptionRowMapper;
import com.carpapapa.mapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chishingchin on 3/3/16.
 */

@Repository
public class MySQLProductDAO implements ProductDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final int OFFSET = 0;
    private final int LIMIT = 10;

    private final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ?";
    private final String GET_PRODUCT_COUNT = "SELECT COUNT(*) FROM products ";
    private final String CREATE_PRODUCT = "INSERT INTO products (`state`, `make`, `model`, `vin`, `ex_color`, `in_color`, `year`, `mileage`, `price`, `engine`, `horse_power`, `torque`, `drivetrain`, `mpg_city`, `mpg_highway`, `status`, `description`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_PRODUCT = "UPDATE products SET `state` = ?, `make` = ?, `model` = ?, `vin` = ?, `ex_color` = ?, `in_color` = ?, `year` = ? , `mileage` = ?, `price` = ?, `engine` = ?, `horse_power` = ?, `torque` = ?, `drivetrain` = ?, `mpg_city` = ?, `mpg_highway` = ?, `status` = ?, `description` = ? WHERE id = ?";
    private String GET_ACTIVE_PRODUCTS = "SELECT * FROM products WHERE state = 'LIVE' ";
    private String GET_PRODUCTS = "SELECT * FROM products ";
    private String GET_PRODUCT_OPTIONS_BY_ID = "SELECT * FROM product_options WHERE product_id = ?";
    private String DELETE_PRODUCT_OPTIONS_BY_ID = "DELETE FROM product_options WHERE product_id = ?";
    private String CREATE_PRODUCT_OPTIONS = "INSERT INTO product_options (`product_id`, `type`, `option`, `description`) ";
    private String DELETE_PRODUCT_BY_ID = "UPDATE products SET `is_deleted` = TRUE WHERE id = ?";
    private String SET_SOLD_DATE = "UPDATE products SET `sold_date` = NOW() WHERE id = ?";

    @Override
    public Products getProducts(int offset, int limit, boolean all, Integer minPrice, Integer maxPrice, String make, String exColor, String status, Long soldAfter, Long soldBefore) {

        String query = GET_PRODUCTS;
        String countQuery = GET_PRODUCT_COUNT;

        List<Object> params = new ArrayList<>();

        query += "WHERE is_deleted = FALSE AND ";
        countQuery += "WHERE is_deleted = FALSE AND ";

        if (!all) {
            query += "state = TRUE AND ";
            countQuery += "state = TRUE AND ";
        }

        if (minPrice != null) {
            query += "price >= ? AND ";
            countQuery += "price >= ? AND ";
            params.add(minPrice);
        }

        if (maxPrice != null) {
            query += "price <= ? AND ";
            countQuery += "price <= ? AND ";
            params.add(maxPrice);
        }

        if (make != null) {
            query += "make = ? AND ";
            countQuery += "make = ? AND ";
            params.add(make);
        }

        if (exColor != null) {
            query += "ex_color = ? AND ";
            countQuery += "ex_color = ? AND ";
            params.add(exColor);
        }

        if (status != null) {
            query += "status = ? AND ";
            countQuery += "status = ? AND ";
            params.add(status);
        }

        if (soldAfter != null) {
            query += "sold_date >= FROM_UNIXTIME(?) AND ";
            countQuery += "sold_date >= FROM_UNIXTIME(?) AND ";
            params.add(soldAfter);
        }

        if (soldBefore != null) {
            query += "sold_date <= FROM_UNIXTIME(?) AND ";
            countQuery += "sold_date <= FROM_UNIXTIME(?) AND ";
            params.add(soldBefore);
        }

        Integer productCount = getProductCount(countQuery.substring(0, countQuery.length() - 4), params);

        query = query.substring(0, query.length() - 4) + "ORDER BY id DESC LIMIT ? OFFSET ?";
        params.add(limit == 0 ? LIMIT : limit);
        params.add(offset == 0 ? OFFSET : offset);
        List<Product> products = jdbcTemplate.query(query, params.toArray(), new ProductRowMapper());

        return new Products(products, productCount);
    }

    @Override
    public Product getProductById(long id) {
        Product result;

        try {
            result = jdbcTemplate.queryForObject(GET_PRODUCT_BY_ID, new Object[]{id}, new ProductRowMapper());
        } catch (EmptyResultDataAccessException e) {
            result = null;
        }

        return result;
    }

    @Override
    public Product createProduct(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(CREATE_PRODUCT, new String[] {"id"});
                    ps.setBoolean(1, product.getState());
                    ps.setString(2, product.getMake());
                    ps.setString(3, product.getModel());
                    ps.setString(4, product.getVin());
                    ps.setString(5, product.getExColor());
                    ps.setString(6, product.getInColor());
                    ps.setInt(7, product.getYear());
                    ps.setInt(8, product.getMileage());
                    ps.setInt(9, product.getPrice());
                    ps.setString(10, product.getEngine());
                    ps.setInt(11, product.getHorsePower());
                    ps.setInt(12, product.getTorque());
                    ps.setString(13, product.getDrivetrain());
                    ps.setInt(14, product.getMpgCity());
                    ps.setInt(15, product.getMpgHighway());
                    ps.setString(16, product.getStatus().name());
                    ps.setString(17, product.getDescription());
                    return ps;
                }, keyHolder);

        product.setId(keyHolder.getKey().longValue());

        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT);
                    ps.setBoolean(1, product.getState());
                    ps.setString(2, product.getMake());
                    ps.setString(3, product.getModel());
                    ps.setString(4, product.getVin());
                    ps.setString(5, product.getExColor());
                    ps.setString(6, product.getInColor());
                    ps.setInt(7, product.getYear());
                    ps.setInt(8, product.getMileage());
                    ps.setInt(9, product.getPrice());
                    ps.setString(10, product.getEngine());
                    ps.setInt(11, product.getHorsePower());
                    ps.setInt(12, product.getTorque());
                    ps.setString(13, product.getDrivetrain());
                    ps.setInt(14, product.getMpgCity());
                    ps.setInt(15, product.getMpgHighway());
                    ps.setString(16, product.getStatus().name());
                    ps.setString(17, product.getDescription());
                    ps.setLong(18, product.getId());
                    return ps;
                }
        );

        if (product.getStatus().equals(ProductStatus.SOLD)) {
            setSoldDate(product.getId());
        }

        return getProductById(product.getId());
    }

    @Override
    public List<ProductOption> getProductOptionsById(long id) {
        List<ProductOption> result;

        try {
            result = jdbcTemplate.query(GET_PRODUCT_OPTIONS_BY_ID, new Object[]{id}, new ProductOptionRowMapper());
        } catch (EmptyResultDataAccessException e) {
            result = new ArrayList<>();
        }

        return result;
    }

    @Override
    public void ensureProductOptions(long id, List<ProductOption> options) {
        jdbcTemplate.update(DELETE_PRODUCT_OPTIONS_BY_ID, new Object[]{id});

        StringBuilder sb = new StringBuilder(CREATE_PRODUCT_OPTIONS);

        if (options != null && options.size() > 0) {
            sb.append("VALUES ");
            for (int i = 0; i < options.size(); i++) {
                if (i == 0) {
                    sb.append("(" + id + ", '" +
                            options.get(i).getType() + "', '" +
                            options.get(i).getOption() + "', '" +
                            options.get(i).getDescription() + "') ");
                } else {
                    sb.append(", (" + id + ", '" +
                            options.get(i).getType() + "', '" +
                            options.get(i).getOption() + "', '" +
                            options.get(i).getDescription() + "') ");
                }
            }
        }

        jdbcTemplate.update(sb.toString());
    }

    @Override
    public Products searchProducts(int offset, int limit, Boolean state, String make, String model, String exColor, Integer year, String status) {
        String query = GET_PRODUCTS;
        String countQuery = GET_PRODUCT_COUNT;

        List<Object> params = new ArrayList<>();

        query += "WHERE is_deleted = FALSE AND ";
        countQuery += "WHERE is_deleted = FALSE AND ";

        if (state != null) {
            query += "state = ? AND ";
            countQuery += "state = ? AND ";
            params.add(state);
        }

        if (make != null) {
            query += "LOWER(make) LIKE LOWER(?) AND ";
            countQuery += "LOWER(make) LIKE LOWER(?) AND ";
            params.add(make + "%");
        }

        if (model != null) {
            query += "LOWER(model) LIKE LOWER(?) AND ";
            countQuery += "LOWER(model) LIKE LOWER(?) AND ";
            params.add(model + "%");
        }

        if (exColor != null) {
            query += "LOWER(ex_color) LIKE LOWER(?) AND ";
            countQuery += "LOWER(ex_color) LIKE LOWER(?) AND ";
            params.add(exColor + "%");
        }

        if (year != null) {
            query += "year = ? AND ";
            countQuery += "year = ? AND ";
            params.add(year);
        }

        if (status != null) {
            query += "status = ? AND ";
            countQuery += "status = ? AND ";
            params.add(status);
        }

        Integer productCount = getProductCount(countQuery.substring(0, countQuery.length() - 4), params);

        query = query.substring(0, query.length() - 4) + "ORDER BY id DESC LIMIT ? OFFSET ?";
        params.add(limit == 0 ? LIMIT : limit);
        params.add(offset == 0 ? OFFSET : offset);
        List<Product> products = jdbcTemplate.query(query, params.toArray(), new ProductRowMapper());

        return new Products(products, productCount);
    }

    @Override
    public void deleteProductById(long id) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCT_BY_ID);
                    ps.setLong(1, id);
                    return ps;
                }
        );
    }

    private Integer getProductCount(String query, List<Object> params) {
        Integer result = jdbcTemplate.queryForObject(query, params.toArray(), Integer.class);


        return result;
    }

    private void setSoldDate(long id) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(SET_SOLD_DATE);
                    ps.setLong(1, id);
                    return ps;
                }
        );
    }
}
