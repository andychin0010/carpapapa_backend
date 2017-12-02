package com.carpapapa.controller;

import com.carpapapa.domain.Customer;
import com.carpapapa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chandler on 6/25/17.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity<?> getCustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<?> getCustomer(@PathVariable long id) {
        Customer customer = customerService.getCustomerById(id);

        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer Not Found.", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    ResponseEntity<?> getCustomerCount() {
        return new ResponseEntity<>(customerService.getCustomerCount(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    ResponseEntity<?> searchCustomers(@RequestParam String searchTerm) {
        return new ResponseEntity<>(customerService.searchCustomers(searchTerm), HttpStatus.OK);
    }
}
