package com.carpapapa.service;

import com.carpapapa.dao.CustomerDAO;
import com.carpapapa.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chandler on 6/25/17.
 */
@Service
public class DefaultCustomerService implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public Integer getCustomerCount() {
        return customerDAO.getCustomerCount();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDAO.createCustomer(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    @Override
    public List<Customer> searchCustomers(String searchTerm) { return customerDAO.searchCustomers(searchTerm); }
}
