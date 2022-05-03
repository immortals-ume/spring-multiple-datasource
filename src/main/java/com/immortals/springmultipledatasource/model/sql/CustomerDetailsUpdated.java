package com.immortals.springmultipledatasource.model.sql;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "customer_details_updated", schema = "customerDb")
public class CustomerDetailsUpdated{


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cust_id", nullable =
            false)
    private long custId;

    @Basic
    @Column(name = "doj", nullable = false)
    private Timestamp doj;

    @Basic
    @Column(name = "cust_name", nullable = false, length = 200)
    private String custName;

}
