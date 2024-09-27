package com.example.transactionservice.gateways.impl;

import com.example.transactionservice.gateways.MerchantGateway;
import com.example.transactionservice.gateways.entities.Merchant;
import com.example.transactionservice.gateways.repositories.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantGatewayImpl implements MerchantGateway {
    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public Optional<Merchant> findByMerchantName(final String merchantName) {
        return merchantRepository.findByMerchantName(merchantName);
    }
}
