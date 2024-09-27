package com.example.transactionservice.gateways.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String accountId;

    @Column(name = "food_balance", nullable = false)
    private BigDecimal foodBalance;

    @Column(name = "meal_balnce", nullable = false)
    private BigDecimal maelBalance;

    @Column(name = "cash_balance", nullable = false)
    private BigDecimal cashBalance;
}
