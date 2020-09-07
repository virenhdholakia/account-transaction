package com.coding.challenge.transactions.entity;

import com.coding.challenge.transactions.enums.AccountTypes;
import com.coding.challenge.transactions.enums.Currency;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accountdetail")
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String customerId;

    private String accountNumber;

    private String accountName;

    private AccountTypes accountType;

    private Date balanceDate;

    private Currency currency;

    private String balance;

}
