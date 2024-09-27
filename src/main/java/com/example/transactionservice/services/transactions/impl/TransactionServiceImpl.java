package com.example.transactionservice.services.transactions.impl;

import ch.qos.logback.core.util.StringUtil;
import com.example.transactionservice.domains.request.TransactionRequest;
import com.example.transactionservice.domains.responses.TransactionAccountResponse;
import com.example.transactionservice.domains.responses.TransactionResponse;
import com.example.transactionservice.enums.BalanceType;
import com.example.transactionservice.enums.MCC;
import com.example.transactionservice.enums.TransactionCode;
import com.example.transactionservice.exceptions.InvalidFieldException;
import com.example.transactionservice.gateways.MerchantGateway;
import com.example.transactionservice.gateways.TransactionGateway;
import com.example.transactionservice.gateways.entities.Merchant;
import com.example.transactionservice.services.accounts.AccountService;
import com.example.transactionservice.services.transactions.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.example.transactionservice.util.constants.Constants.*;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionGateway transactionGateway;
    @Autowired
    private MerchantGateway merchantGateway;

    @Override
    public TransactionAccountResponse getTransactionsByAccount(final String accountId) {
        if (!StringUtils.hasText(accountId)) {
            throw new InvalidFieldException(MESSAGE_INVALID_FIELD_VALUE, Set.of(accountId));
        }
        final var transactions = transactionGateway.getTransactionByAccount(accountId);
        return TransactionAccountResponse.builder()
                .transactions(transactions)
                .build();
    }

    @Override
    public TransactionResponse authorizeTransaction(TransactionRequest transactionRequest) {
        validateRequest(transactionRequest);
        final String accountId = transactionRequest.getAccountId();
        final BigDecimal totalAmount = transactionRequest.getTotalAmount();
        ;
        final String merchantName = transactionRequest.getMerchant();

        Optional<Merchant> merchant = merchantGateway.findByMerchantName(merchantName);

        final String mcc = merchant.isPresent() ? merchant.get().getMcc() : transactionRequest.getMcc();

        final BalanceType balanceType = MCC.getBalanceType(mcc);
        log.info(MESSAGE_SELECTED_BALANCE, balanceType);
        boolean authorized = accountService.authorizeTransaction(accountId, totalAmount, balanceType);

        String code;
        if (authorized) {
            code = TransactionCode.APPROVED.getCode();
            log.info(MESSAGE_SAVING_TRANSACTION_AUTHORIZED);
            transactionGateway.saveTransaction(transactionRequest);
        } else {
            code = TransactionCode.INSUFFICIENT_FUND.getCode();
        }
        return TransactionResponse.builder().code(code).build();
    }

    private void validateRequest(TransactionRequest transactionRequest) {
        Set<String> invalidFields = new HashSet<>();

        if (transactionRequest == null) {
            invalidFields.add("Transaction request body is null");
        } else {
            if (!StringUtils.hasText(transactionRequest.getAccountId())) {
                invalidFields.add("accountId");
            }
            if (transactionRequest.getTotalAmount() == null || transactionRequest.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
                invalidFields.add("totalAmount");
            }
            if (!StringUtils.hasText(transactionRequest.getMerchant())) {
                invalidFields.add("merchant");
            }
        }
        if (!invalidFields.isEmpty()) {
            throw new InvalidFieldException(MESSAGE_INVALID_FIELD_VALUE, invalidFields);
        }
    }
}
