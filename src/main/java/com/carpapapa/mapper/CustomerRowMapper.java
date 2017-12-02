package com.carpapapa.mapper;

import com.carpapapa.domain.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by chandler on 6/25/17.
 */
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setName(resultSet.getString("name"));
        customer.setPhone(resultSet.getString("phone"));
        customer.setEmail(resultSet.getString("email"));
        customer.setWechat(resultSet.getString("wechat"));
        customer.setNotes(resultSet.getString("notes"));
        customer.setCreationTimestamp(resultSet.getLong("creation_timestamp"));
        customer.setModificationTimestamp(resultSet.getLong("modification_timestamp"));
        return customer;
    }
}