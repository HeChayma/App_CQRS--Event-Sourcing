package com.example.accountservice.commonapi.dtos;

import lombok.Data;

@Data
public class CreditAccountRequest {
    private double amount;
    private String currency;
}
