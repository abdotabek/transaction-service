package com.example.transactionservice.gateways.repositories;

import com.example.transactionservice.gateways.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByAccountId(final String accountId);
}
