package com.example.transactionservice.gateways;

import com.example.transactionservice.domains.request.TransactionRequest;
import com.example.transactionservice.gateways.entities.Transaction;

import java.util.List;

public interface TransactionGateway {
    void saveTransaction(final TransactionRequest transactionRequest);

    List<Transaction> getTransactionByAccount(final String accountId);
}
