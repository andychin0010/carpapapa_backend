package com.carpapapa.mapper;

import com.carpapapa.domain.ProductOption;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by chandler on 5/12/17.
 */
public class ProductOptionRowMapper implements RowMapper<ProductOption> {
    @Override
    public ProductOption mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductOption retval = new ProductOption();
        retval.setId(resultSet.getLong("id"));
        retval.setProductId(resultSet.getLong("product_id"));
        retval.setType(resultSet.getString("type"));
        retval.setOption(resultSet.getString("option"));
        retval.setDescription(resultSet.getString("description"));
        retval.setCreationTimestamp(resultSet.getLong("creation_timestamp"));
        retval.setModificationTimestamp(resultSet.getLong("modification_timestamp"));
        return retval;
    }
}