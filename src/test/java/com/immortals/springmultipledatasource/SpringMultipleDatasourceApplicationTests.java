package com.immortals.springmultipledatasource;

import com.immortals.springmultipledatasource.controller.CustomerController;
import com.immortals.springmultipledatasource.controller.CustomerUpdateController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringMultipleDatasourceApplicationTests{


    @Autowired
    private CustomerController customerController;

    @Autowired
    private CustomerUpdateController customerUpdateController;

    @Test
    void contextLoads(){
        assertNotNull( customerController );
        assertNotNull( customerUpdateController );
    }

}
