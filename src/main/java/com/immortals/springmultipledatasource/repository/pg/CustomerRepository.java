package com.immortals.springmultipledatasource.repository.pg;

import com.immortals.springmultipledatasource.model.pg.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository< CustomerDetails,Long>{
}
