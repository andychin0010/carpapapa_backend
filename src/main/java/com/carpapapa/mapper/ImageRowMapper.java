package com.carpapapa.mapper;

import com.carpapapa.domain.Image;
import com.carpapapa.domain.ImageType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by chishingchin on 3/10/16.
 */
public class ImageRowMapper implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet resultSet, int i) throws SQLException {
        Image image = new Image();
        image.setId(resultSet.getLong("id"));
        image.setProductId(resultSet.getLong("product_id"));
        image.setPath(resultSet.getString("path"));
        image.setType(ImageType.valueOf(resultSet.getString("type")));
        image.setOrder(resultSet.getInt("order"));
        image.setApproved(resultSet.getBoolean("approved"));
        image.setCreationTimestamp(resultSet.getLong("creation_timestamp"));
        image.setModificationTimestamp(resultSet.getLong("modification_timestamp"));
        return image;
    }
}
