package com.example.transactionservice.domains.responses;

import com.example.transactionservice.gateways.entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class TransactionAccountResponse {
    private List<Transaction> transactions;
}
