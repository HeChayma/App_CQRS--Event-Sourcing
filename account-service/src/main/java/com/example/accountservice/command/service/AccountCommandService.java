package com.example.accountservice.command.service;

import com.example.accountservice.commonapi.commands.CreateAccountCommand;
import com.example.accountservice.commonapi.commands.CreditAccountCommand;
import com.example.accountservice.commonapi.commands.DebitAccountCommand;
import com.example.accountservice.commonapi.dtos.CreateAccountRequest;
import com.example.accountservice.commonapi.dtos.CreditAccountRequest;
import com.example.accountservice.commonapi.dtos.DebitAccountRequest;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class AccountCommandService {
    private final CommandGateway commandGateway;

    public CompletableFuture<String> createAccount(CreateAccountRequest request) {
        return commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                request.getInitialBalance(),
                request.getCurrency()));
    }

    public CompletableFuture<String> creditAccount(String accountId, CreditAccountRequest request) {
        return commandGateway.send(new CreditAccountCommand(
                accountId,
                request.getAmount(),
                request.getCurrency()));
    }

    public CompletableFuture<String> debitAccount(String accountId, DebitAccountRequest request) {
        return commandGateway.send(new DebitAccountCommand(
                accountId,
                request.getAmount(),
                request.getCurrency()));
    }
}
