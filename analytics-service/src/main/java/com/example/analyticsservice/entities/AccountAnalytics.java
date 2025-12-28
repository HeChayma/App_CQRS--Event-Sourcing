package com.example.analyticsservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountAnalytics {
    @Id
    private String accountId;
    private double totalCredited;
    private double totalDebited;
    private double currentBalance;
    private long transactionCount;
}
