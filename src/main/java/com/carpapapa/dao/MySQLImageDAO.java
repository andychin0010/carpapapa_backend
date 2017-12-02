package com.carpapapa.dao;

import com.carpapapa.domain.Image;
import com.carpapapa.domain.ImageType;
import com.carpapapa.mapper.ImageRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chishingchin on 3/10/16.
 */

@Repository
public class MySQLImageDAO implements ImageDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String CREATE_IMAGE = "INSERT INTO product_images (`product_id`, `path`, `type`, `order`) VALUES(?, ?, ?, ?)";
    private final String UPDATE_IMAGE = "UPDATE product_images SET `type` = ?, `order` = ? WHERE `id` = ?";
    private final String GET_IMAGES_BY_PRODUCT_ID = "SELECT * FROM product_images WHERE `product_id` = ? ORDER BY `order`, `type` ASC";
    private final String GET_IMAGES_BY_PRODUCT_ID_APPROVED = "SELECT * FROM product_images WHERE `product_id` = ? AND `approved` = true ORDER BY `order`, `type` ASC";
    private final String GET_IMAGES_BY_PRODUCT_ID_FILTER_TYPE = "SELECT * FROM product_images WHERE `product_id` = ? AND `type` = ?";
    private final String GET_IMAGES_BY_PRODUCT_ID_FILTER_TYPE_APPROVED = "SELECT * FROM product_images WHERE `product_id` = ? AND `type` = ? AND `approved` = true";
    private final String GET_LAST_IMAGES_ORDER = "SELECT `order` FROM product_images WHERE `product_id` = ? ORDER BY `order` DESC LIMIT 1";
    private final String DELETE_IMAGE = "DELETE FROM product_images WHERE id = ?";
    private final String APPROVE_IMAGE = "UPDATE product_images SET `approved` = true WHERE `id` = ?";

    @Override
    public synchronized void createProductImage(long productId, String path, ImageType type) {
        jdbcTemplate.update(CREATE_IMAGE, new Object[]{productId, path, type.name(), incrementOrder(productId)});
    }

    @Override
    public void updateProductImage(long id, ImageType type, int order) {
        jdbcTemplate.update(UPDATE_IMAGE, new Object[]{type.name(), order, id});
    }

    @Override
    public void approveProductImage(long id) {
        jdbcTemplate.update(APPROVE_IMAGE, new Object[]{id});
    }

    @Override
    public void deleteProductImage(long id) {
        jdbcTemplate.update(DELETE_IMAGE, id);
    }

    @Override
    public List<Image> getImagesByProductId(long productId, boolean approved) {
        List<Image> result = new ArrayList<>();

        try {
            if (approved) {
                result = jdbcTemplate.query(GET_IMAGES_BY_PRODUCT_ID_APPROVED, new Object[]{productId}, new ImageRowMapper());
            } else {
                result = jdbcTemplate.query(GET_IMAGES_BY_PRODUCT_ID, new Object[]{productId}, new ImageRowMapper());
            }
        } catch (EmptyResultDataAccessException e) {
        }

        return result;
    }

    @Override
    public List<Image> getImagesByProductIdFilterType(long productId, ImageType type, boolean approved) {
        List<Image> result = new ArrayList<>();

        try {
            if (approved) {
                result = jdbcTemplate.query(GET_IMAGES_BY_PRODUCT_ID_FILTER_TYPE_APPROVED, new Object[]{productId, type.name()}, new ImageRowMapper());
            } else {
                result = jdbcTemplate.query(GET_IMAGES_BY_PRODUCT_ID_FILTER_TYPE, new Object[]{productId, type.name()}, new ImageRowMapper());
            }
        } catch (EmptyResultDataAccessException e) {
        }

        return result;
    }

    private int incrementOrder(long productId) {
        int result = 0;

        try {
            result = jdbcTemplate.queryForObject(GET_LAST_IMAGES_ORDER, new Object[]{productId}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
        }

        return ++result;
    }
}
