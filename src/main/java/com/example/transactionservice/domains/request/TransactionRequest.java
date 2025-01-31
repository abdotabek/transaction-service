package com.example.transactionservice.domains.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionRequest implements Serializable {
    @JsonProperty("accountId")
    private String accountId;

    @JsonProperty("totalAmount")
    private BigDecimal totalAmount;

    @JsonProperty("mcc")
    private String mcc;

    @JsonProperty("merchant")
    private String merchant;
}
