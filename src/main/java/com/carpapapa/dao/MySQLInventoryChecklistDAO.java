package com.carpapapa.dao;

import com.carpapapa.domain.InventoryCheck;
import com.carpapapa.mapper.InventoryCheckRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by chandler on 4/11/18.
 */

@Repository
public class MySQLInventoryChecklistDAO implements InventoryChecklistDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private String CHECK_VIN = "INSERT INTO `inventory_checklist` SET `vin` = ? ON DUPLICATE KEY UPDATE `last_check_timestamp` = NOW(), `is_deleted` = 0";
    private String GET_CHECKLIST = "SELECT * FROM `inventory_checklist` WHERE `is_deleted` = FALSE;";

    @Override
    public void checkVIN(String vin) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(CHECK_VIN);
                    ps.setString(1, vin);
                    return ps;
                }
        );
    }

    @Override
    public List<InventoryCheck> getChecklist() {
        List<InventoryCheck> inventoryChecks = jdbcTemplate.query(GET_CHECKLIST, new InventoryCheckRowMapper());

        return inventoryChecks;
    }
}
