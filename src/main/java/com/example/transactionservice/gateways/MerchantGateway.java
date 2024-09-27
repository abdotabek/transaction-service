package com.example.transactionservice.gateways;

import com.example.transactionservice.gateways.entities.Merchant;

import java.util.Optional;

public interface MerchantGateway {
    Optional<Merchant> findByMerchantName(String merchantName);
}
