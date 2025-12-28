package com.example.accountservice.commonapi.dtos;

import lombok.Data;

@Data
public class DebitAccountRequest {
    private double amount;
    private String currency;
}
