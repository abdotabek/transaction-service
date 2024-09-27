package com.example.transactionservice.domains.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {
    private String code;

}
