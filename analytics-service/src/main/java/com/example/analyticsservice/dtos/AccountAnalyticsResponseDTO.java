package com.example.analyticsservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountAnalyticsResponseDTO {
    private String accountId;
    private double totalCredited;
    private double totalDebited;
    private double currentBalance;
    private long transactionCount;
}
