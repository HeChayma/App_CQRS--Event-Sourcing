package com.example.accountservice.query.controller;

import com.example.accountservice.commonapi.dtos.AccountResponseDTO;
import com.example.accountservice.query.service.AccountQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/account")
@AllArgsConstructor
public class AccountQueryController {

    private final AccountQueryService accountQueryService;

    @GetMapping("/all")
    public CompletableFuture<List<AccountResponseDTO>> getAllAccounts() {
        return accountQueryService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public CompletableFuture<AccountResponseDTO> getAccountById(@PathVariable String id) {
        return accountQueryService.getAccountById(id);
    }
}
