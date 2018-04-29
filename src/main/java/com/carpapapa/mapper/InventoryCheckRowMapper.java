package com.carpapapa.mapper;

import com.carpapapa.domain.InventoryCheck;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by chandler on 4/14/18.
 */
public class InventoryCheckRowMapper implements RowMapper<InventoryCheck> {
    @Override
    public InventoryCheck mapRow(ResultSet resultSet, int i) throws SQLException {
        InventoryCheck inventoryCheck = new InventoryCheck();
        inventoryCheck.setId(resultSet.getInt("id"));
        inventoryCheck.setVin(resultSet.getString("vin"));
        inventoryCheck.setDeleted(resultSet.getBoolean("is_deleted"));

        if (resultSet.getDate("last_check_timestamp") != null) {
            inventoryCheck.setLastCheckTimestamp(resultSet.getTimestamp("last_check_timestamp").getTime());
        }

        if (resultSet.getDate("modification_timestamp") != null) {
            inventoryCheck.setModificationTimestamp(resultSet.getDate("modification_timestamp").getTime());
        }

        return inventoryCheck;
    }
}
