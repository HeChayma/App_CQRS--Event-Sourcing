package com.example.accountservice.command.aggregate;

import com.example.accountservice.commonapi.commands.CreateAccountCommand;
import com.example.accountservice.commonapi.commands.CreditAccountCommand;
import com.example.accountservice.commonapi.commands.DebitAccountCommand;
import com.example.accountservice.commonapi.events.AccountCreatedEvent;
import com.example.accountservice.commonapi.events.AccountCreditedEvent;
import com.example.accountservice.commonapi.events.AccountDebitedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;

    public AccountAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        if (command.getInitialBalance() < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        apply(new AccountCreatedEvent(command.getId(), command.getInitialBalance(), command.getCurrency()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.accountId = event.getId();
        this.balance = event.getInitialBalance();
        this.currency = event.getCurrency();
    }

    @CommandHandler
    public void handle(CreditAccountCommand command) {
        if (command.getAmount() < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        apply(new AccountCreditedEvent(command.getId(), command.getAmount(), command.getCurrency()));
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent event) {
        this.balance += event.getAmount();
    }

    @CommandHandler
    public void handle(DebitAccountCommand command) {
        if (command.getAmount() < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (this.balance < command.getAmount()) {
            throw new RuntimeException("Insufficient Balance");
        }
        apply(new AccountDebitedEvent(command.getId(), command.getAmount(), command.getCurrency()));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent event) {
        this.balance -= event.getAmount();
    }
}
