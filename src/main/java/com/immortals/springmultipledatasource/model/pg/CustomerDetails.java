package com.immortals.springmultipledatasource.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "customer_details", schema = "customer", catalog = "postgres")
public class CustomerDetails{


@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
@Column(name = "cust_id", nullable = false)
private long custId;

@Column(name = "doj", nullable = false)
private Timestamp doj;

@Column(name = "cust_name", nullable = false, length = 200)
private String custName;

}
