package com.immortals.springmultipledatasource.controller;

import com.immortals.springmultipledatasource.model.pg.CustomerDetails;
import com.immortals.springmultipledatasource.model.sql.CustomerDetailsUpdated;
import com.immortals.springmultipledatasource.repository.pg.CustomerRepository;
import com.immortals.springmultipledatasource.repository.sql.CutomerUpdatedRepository;
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

    @Autowired
    private CutomerUpdatedRepository cutomerUpdatedRepository;

    @GetMapping
    public List< CustomerDetails > getCustomers(){
        return customerRepository.findAll( );
    }

    @GetMapping("/updated")
    public List< CustomerDetailsUpdated > getAllCustomers(){
        return cutomerUpdatedRepository.findAll( );
    }
}
