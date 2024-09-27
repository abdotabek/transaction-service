package com.example.transactionservice.services.transactions;

import com.example.transactionservice.domains.request.TransactionRequest;
import com.example.transactionservice.domains.responses.TransactionAccountResponse;
import com.example.transactionservice.domains.responses.TransactionResponse;

public interface TransactionService {
    TransactionResponse authorizeTransaction(TransactionRequest request);

    TransactionAccountResponse getTransactionsByAccount(final String accountId);

}

