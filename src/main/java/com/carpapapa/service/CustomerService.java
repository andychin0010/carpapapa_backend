package com.carpapapa.service;

import com.carpapapa.domain.Customer;

import java.util.List;

/**
 * Created by chandler on 6/25/17.
 */
public interface CustomerService {

    List<Customer> getCustomers();

    Customer getCustomerById(long id);

    Integer getCustomerCount();

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    List<Customer> searchCustomers(String searchTerm);
}
