package com.carpapapa.controller;

import com.carpapapa.domain.Customer;
import com.carpapapa.service.CustomerService;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by chandler on 6/25/17.
 */
@RestController
@RequestMapping("/")
public class TestController {

//    @Autowired
//    CustomerService customerService;

    @Autowired
    GraphQL graphQL;

    @CrossOrigin
    @RequestMapping(value = "/graphql", method = RequestMethod.POST)
    ResponseEntity<?> test(@RequestParam (required = false) String test) {
        graphQL.execute(test).getData();
        return new ResponseEntity<>(graphQL.execute("test").getData(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    ResponseEntity<?> getCustomer() {
        Map<String, Object> result = graphQL.execute("{hello}").getData();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
