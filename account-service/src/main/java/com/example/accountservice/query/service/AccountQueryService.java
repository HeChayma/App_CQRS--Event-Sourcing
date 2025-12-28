package com.example.accountservice.query.service;

import com.example.accountservice.commonapi.dtos.AccountResponseDTO;
import com.example.accountservice.query.entities.Account;
import com.example.accountservice.query.queries.GetAccountByIdQuery;
import com.example.accountservice.query.queries.GetAllAccountsQuery;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class AccountQueryService {
    private final QueryGateway queryGateway;

    public CompletableFuture<List<AccountResponseDTO>> getAllAccounts() {
        return queryGateway.query(new GetAllAccountsQuery(), ResponseTypes.multipleInstancesOf(Account.class))
                .thenApply(accounts -> accounts.stream()
                        .map(account -> new AccountResponseDTO(
                                account.getId(),
                                account.getBalance(),
                                account.getCurrency()))
                        .toList());
    }

    public CompletableFuture<AccountResponseDTO> getAccountById(String id) {
        return queryGateway.query(new GetAccountByIdQuery(id), ResponseTypes.instanceOf(Account.class))
                .thenApply(account -> account != null ? new AccountResponseDTO(
                        account.getId(),
                        account.getBalance(),
                        account.getCurrency()) : null);
    }
}
