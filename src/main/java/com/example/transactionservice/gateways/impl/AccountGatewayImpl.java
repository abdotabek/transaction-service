package com.example.transactionservice.gateways.impl;

import com.example.transactionservice.gateways.AccountGateway;
import com.example.transactionservice.gateways.entities.Account;
import com.example.transactionservice.gateways.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountGatewayImpl implements AccountGateway {
    @Autowired
    private AccountRepository repository;

    @Override
    public Optional<Account> findById(final String accountId) {
        return repository.findById(accountId);
    }

    public void updateAccount(final Account account) {
        repository.save(account);
    }
}
