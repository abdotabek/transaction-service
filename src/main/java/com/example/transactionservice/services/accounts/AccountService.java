package com.example.transactionservice.services.accounts;

import com.example.transactionservice.enums.BalanceType;

import java.math.BigDecimal;

public interface AccountService {
    boolean authorizeTransaction(final String accountId, final BigDecimal amount, final BalanceType balanceType);
}
