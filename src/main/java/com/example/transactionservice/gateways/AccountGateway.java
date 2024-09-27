package com.example.transactionservice.gateways;

import com.example.transactionservice.gateways.entities.Account;

import java.util.Optional;

public interface AccountGateway {
    Optional<Account> findById(final String accountId);

    void updateAccount(Account dto);

}
