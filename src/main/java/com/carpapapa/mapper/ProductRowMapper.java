package com.carpapapa.mapper;

import com.carpapapa.domain.Product;
import com.carpapapa.domain.ProductStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by chishingchin on 3/3/16.
 */
public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setState(resultSet.getBoolean("state"));
        product.setMake(resultSet.getString("make"));
        product.setModel(resultSet.getString("model"));
        product.setVin(resultSet.getString("vin"));
        product.setExColor(resultSet.getString("ex_color"));
        product.setInColor(resultSet.getString("in_color"));
        product.setYear(resultSet.getInt("year"));
        product.setMileage(resultSet.getInt("mileage"));
        product.setPrice(resultSet.getInt("price"));
        product.setEngine(resultSet.getString("engine"));
        product.setHorsePower(resultSet.getInt("horse_power"));
        product.setTorque(resultSet.getInt("torque"));
        product.setDrivetrain(resultSet.getString("drivetrain"));
        product.setMpgCity(resultSet.getInt("mpg_city"));
        product.setMpgHighway(resultSet.getInt("mpg_highway"));
        product.setStatus(ProductStatus.valueOf(resultSet.getString("status")));
        product.setDescription(resultSet.getString("description"));

        if (resultSet.getDate("sold_date") != null) {
            product.setSoldDate(resultSet.getDate("sold_date").getTime());
        }

        if (resultSet.getDate("creation_timestamp") != null) {
            product.setCreationTimestamp(resultSet.getDate("creation_timestamp").getTime());
        }

        if (resultSet.getDate("modification_timestamp") != null) {
            product.setModificationTimestamp(resultSet.getDate("modification_timestamp").getTime());
        }

        return product;
    }
}
