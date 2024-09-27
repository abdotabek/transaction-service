package com.example.transactionservice.services.accounts.impl;

import com.example.transactionservice.enums.BalanceType;
import com.example.transactionservice.gateways.AccountGateway;
import com.example.transactionservice.gateways.entities.Account;
import com.example.transactionservice.services.accounts.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.example.transactionservice.util.constants.Constants.MESSAGE_BALANCE_NOT_ENOUGH;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountGateway accountGateway;

    public boolean authorizeTransaction(final String accountId, final BigDecimal amount, final BalanceType balanceType) {
        Optional<Account> optionalAccount = accountGateway.findById(accountId);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            BigDecimal balance = getBalanceType(account, balanceType);
            if (balance.compareTo(amount) >= 0) {
                debitBalance(account, balanceType, amount);
                accountGateway.updateAccount(account);
                return true;
            } else if (balanceType != BalanceType.CASH && account.getCashBalance().compareTo(amount) >= 0) {
                log.info(MESSAGE_BALANCE_NOT_ENOUGH);
                debitBalance(account, BalanceType.CASH, amount);
                accountGateway.updateAccount(account);
                return true;
            }
        }
        return false;

    }

    private BigDecimal getBalanceType(Account account, BalanceType balanceType) {
        return switch (balanceType) {
            case FOOD -> account.getFoodBalance();
            case MEAL -> account.getMaelBalance();
            case CASH -> account.getCashBalance();
        };
    }

    private void debitBalance(Account account, BalanceType balanceType, BigDecimal amount) {
        switch (balanceType) {
            case FOOD:
                account.setFoodBalance(account.getFoodBalance().subtract(amount));
            case MEAL:
                account.setMaelBalance(account.getMaelBalance().subtract(amount));
            case CASH:
                account.setCashBalance(account.getCashBalance().subtract(amount));
                break;
        }
    }

}
