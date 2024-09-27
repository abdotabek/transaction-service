package com.example.transactionservice.mappers;

import com.example.transactionservice.domains.request.TransactionRequest;
import com.example.transactionservice.gateways.entities.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction requestToEntity(TransactionRequest request);
}
