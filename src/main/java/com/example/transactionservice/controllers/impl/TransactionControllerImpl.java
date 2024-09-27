package com.example.transactionservice.controllers.impl;

import com.example.transactionservice.controllers.TransactionController;
import com.example.transactionservice.domains.request.TransactionRequest;
import com.example.transactionservice.domains.responses.TransactionAccountResponse;
import com.example.transactionservice.domains.responses.TransactionResponse;
import com.example.transactionservice.services.transactions.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transaction Service")
public class TransactionControllerImpl implements TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/authorize")
    @Override
    public ResponseEntity<TransactionResponse> authorizeTransaction(@RequestBody TransactionRequest request) {
        final var response = transactionService.authorizeTransaction(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/account/{account_id}")
    @Override
    public ResponseEntity<TransactionAccountResponse> getTransactionByAccount(@PathVariable("account_id") final String accountId) {
        final var response = transactionService.getTransactionsByAccount(accountId);
        return ResponseEntity.ok(response);
    }

}
