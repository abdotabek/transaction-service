package com.example.transactionservice.gateways.impl;

import com.example.transactionservice.domains.request.TransactionRequest;
import com.example.transactionservice.gateways.TransactionGateway;
import com.example.transactionservice.gateways.entities.Transaction;
import com.example.transactionservice.gateways.repositories.TransactionRepository;
import com.example.transactionservice.mappers.TransactionMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.example.transactionservice.util.constants.Constants.FOUND_TRANSACTIONS_ACCOUNT;
import static com.example.transactionservice.util.constants.Constants.STARTING_SEARCH_TRANSACTION_ACCOUNT;

@Service
@Slf4j
public class TransactionGatewayImpl implements TransactionGateway {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper mapper;


    @Override
    @Transactional
    public void saveTransaction(TransactionRequest transactionRequest) {
        var entity = mapper.requestToEntity(transactionRequest);
        transactionRepository.save(entity);
    }

    @Override
    @Transactional
    public List<Transaction> getTransactionByAccount(final String accountId) {
        log.info(STARTING_SEARCH_TRANSACTION_ACCOUNT, accountId);
        final var transactions = transactionRepository.findByAccountId(accountId);
        log.info(FOUND_TRANSACTIONS_ACCOUNT, transactions.size(), accountId);
        return transactions;
    }


}
