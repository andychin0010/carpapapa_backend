package com.carpapapa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

/**
 * Created by chandler on 4/28/17.
 */

@Repository
public class MySQLSubscriptionDAO implements SubscriptionDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SUBSCRIBE = "INSERT INTO subscriptions (`email`) VALUES(?)";

    @Override
    public void subscribe(String email) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(SUBSCRIBE);
                    ps.setString(1, email);
                    return ps;
                });
    }
}
