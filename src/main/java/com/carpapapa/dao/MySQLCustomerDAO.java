package com.carpapapa.dao;

import com.carpapapa.domain.Customer;
import com.carpapapa.mapper.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by chandler on 6/25/17.
 */
@Repository
public class MySQLCustomerDAO implements CustomerDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String GET_CUSTOMERS = "SELECT * FROM customers WHERE is_deleted = FALSE ORDER BY id DESC";
    private final String GET_CUSTOMER_BY_ID = "SELECT * FROM customers WHERE id = ?";
    private final String GET_CUSTOMER_COUNT = "SELECT COUNT(*) FROM customers WHERE is_deleted = FALSE";
    private final String CREATE_CUSTOMER = "INSERT INTO customers (`name`, `phone`, `email`, `wechat`, `notes`) VALUES(?, ?, ?, ?, ?)";
    private final String UPDATE_CUSTOMER = "UPDATE customers SET `name` = ?, `phone` = ?, `email` = ?, `wechat` = ?, `notes` = ? WHERE id = ?";
    private final String SEARCH_CUSTOMERS = "SELECT * FROM customers WHERE (name LIKE ? OR phone LIKE ? OR email LIKE ? OR wechat LIKE ?) AND is_delete = FALSE ORDER BY id DESC";

    @Override
    public List<Customer> getCustomers() {
        return jdbcTemplate.query(GET_CUSTOMERS, new Object[]{}, new CustomerRowMapper());
    }

    @Override
    public Customer getCustomerById(long id) {
        Customer result;

        try {
            result = jdbcTemplate.queryForObject(GET_CUSTOMER_BY_ID, new Object[]{id}, new CustomerRowMapper());
        } catch (EmptyResultDataAccessException e) {
            result = null;
        }

        return result;
    }

    @Override
    public Integer getCustomerCount() {
        Integer result = 0;

        try {
            result = jdbcTemplate.queryForObject(GET_CUSTOMER_COUNT, new Object[]{}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            result = 0;
        }

        return result;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(CREATE_CUSTOMER, new String[] {"id"});
                    ps.setString(1, customer.getName());
                    ps.setString(2, customer.getPhone());
                    ps.setString(3, customer.getEmail());
                    ps.setString(4, customer.getWechat());
                    ps.setString(5, customer.getNotes());
                    return ps;
                }, keyHolder);

        customer.setId(keyHolder.getKey().longValue());

        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(UPDATE_CUSTOMER);
                    ps.setString(1, customer.getName());
                    ps.setString(2, customer.getPhone());
                    ps.setString(3, customer.getEmail());
                    ps.setString(4, customer.getWechat());
                    ps.setString(5, customer.getNotes());
                    ps.setLong(6, customer.getId());
                    return ps;
                }
        );

        return getCustomerById(customer.getId());
    }

    @Override
    public List<Customer> searchCustomers(String searchTerm) {
        searchTerm = searchTerm + "%";

        return jdbcTemplate.query(SEARCH_CUSTOMERS, new Object[]{searchTerm, searchTerm, searchTerm, searchTerm}, new CustomerRowMapper());
    }
}
