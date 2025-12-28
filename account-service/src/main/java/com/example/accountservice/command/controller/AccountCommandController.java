package com.example.accountservice.command.controller;

import com.example.accountservice.command.service.AccountCommandService;
import com.example.accountservice.commonapi.dtos.CreateAccountRequest;
import com.example.accountservice.commonapi.dtos.CreditAccountRequest;
import com.example.accountservice.commonapi.dtos.DebitAccountRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/account")
@AllArgsConstructor
public class AccountCommandController {

    private final AccountCommandService accountCommandService;

    @PostMapping("/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequest request) {
        return accountCommandService.createAccount(request);
    }

    @PutMapping("/credit/{accountId}")
    public CompletableFuture<String> creditAccount(@PathVariable String accountId,
            @RequestBody CreditAccountRequest request) {
        return accountCommandService.creditAccount(accountId, request);
    }

    @PutMapping("/debit/{accountId}")
    public CompletableFuture<String> debitAccount(@PathVariable String accountId,
            @RequestBody DebitAccountRequest request) {
        return accountCommandService.debitAccount(accountId, request);
    }
}
