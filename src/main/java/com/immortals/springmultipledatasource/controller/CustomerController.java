package com.immortals.springmultipledatasource.controller;

import com.immortals.springmultipledatasource.model.pg.CustomerDetails;
import com.immortals.springmultipledatasource.repository.pg.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController{


    @Autowired
    private CustomerRepository customerRepository;



    @GetMapping
    public List< CustomerDetails > getCustomers(){
        return customerRepository.findAll( );
    }


}
