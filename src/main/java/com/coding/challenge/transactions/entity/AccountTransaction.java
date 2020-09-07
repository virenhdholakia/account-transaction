package com.coding.challenge.transactions.entity;

import com.coding.challenge.transactions.enums.Currency;
import com.coding.challenge.transactions.enums.TransactionType;
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
@Table(name = "accounttransactions")
public class AccountTransaction {

    @Id
    @GeneratedValue
    private Long id;

    private String accountNumber;

    private Date transactionDate;

    private Currency transactionCurrency;

    private String amount;

    private TransactionType transactionType;

    private String transactionNarrative;

}
