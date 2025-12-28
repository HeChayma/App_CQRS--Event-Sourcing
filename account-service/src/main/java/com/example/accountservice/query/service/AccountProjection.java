package com.example.accountservice.query.service;

import com.example.accountservice.commonapi.events.AccountCreatedEvent;
import com.example.accountservice.commonapi.events.AccountCreditedEvent;
import com.example.accountservice.commonapi.events.AccountDebitedEvent;
import com.example.accountservice.query.entities.Account;
import com.example.accountservice.query.queries.GetAccountByIdQuery;
import com.example.accountservice.query.queries.GetAllAccountsQuery;
import com.example.accountservice.query.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class AccountProjection {
    private AccountRepository accountRepository;

    @EventHandler
    public void on(AccountCreatedEvent event) {
        log.info("AccountCreatedEvent received: {}", event.getId());
        Account account = new Account(event.getId(), event.getInitialBalance(), event.getCurrency());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountCreditedEvent event) {
        log.info("AccountCreditedEvent received: {}", event.getId());
        Account account = accountRepository.findById(event.getId()).orElse(null);
        if (account != null) {
            account.setBalance(account.getBalance() + event.getAmount());
            accountRepository.save(account);
        }
    }

    @EventHandler
    public void on(AccountDebitedEvent event) {
        log.info("AccountDebitedEvent received: {}", event.getId());
        Account account = accountRepository.findById(event.getId()).orElse(null);
        if (account != null) {
            account.setBalance(account.getBalance() - event.getAmount());
            accountRepository.save(account);
        }
    }

    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query) {
        return accountRepository.findAll();
    }

    @QueryHandler
    public Account on(GetAccountByIdQuery query) {
        return accountRepository.findById(query.getId()).orElse(null);
    }
}
