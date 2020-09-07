package com.coding.challenge.transactions.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Customer {

    private String customerId;

    private String customerName;

}
