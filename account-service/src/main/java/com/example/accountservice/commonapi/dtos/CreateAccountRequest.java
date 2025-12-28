package com.example.accountservice.commonapi.dtos;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private double initialBalance;
    private String currency;
}
