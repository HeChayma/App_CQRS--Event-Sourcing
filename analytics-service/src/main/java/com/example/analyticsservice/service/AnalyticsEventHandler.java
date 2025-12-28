package com.example.analyticsservice.service;

import com.example.accountservice.commonapi.events.AccountCreatedEvent;
import com.example.accountservice.commonapi.events.AccountCreditedEvent;
import com.example.accountservice.commonapi.events.AccountDebitedEvent;
import com.example.analyticsservice.entities.AccountAnalytics;
import com.example.analyticsservice.repository.AnalyticsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AnalyticsEventHandler {
    private AnalyticsRepository analyticsRepository;

    @EventHandler
    public void on(AccountCreatedEvent event) {
        log.info("Analytics - AccountCreatedEvent: {}", event.getId());
        AccountAnalytics analytics = new AccountAnalytics(
                event.getId(),
                0,
                0,
                event.getInitialBalance(),
                0);
        analyticsRepository.save(analytics);
    }

    @EventHandler
    public void on(AccountCreditedEvent event) {
        log.info("Analytics - AccountCreditedEvent: {}", event.getId());
        AccountAnalytics analytics = analyticsRepository.findById(event.getId()).orElse(null);
        if (analytics != null) {
            analytics.setTotalCredited(analytics.getTotalCredited() + event.getAmount());
            analytics.setCurrentBalance(analytics.getCurrentBalance() + event.getAmount());
            analytics.setTransactionCount(analytics.getTransactionCount() + 1);
            analyticsRepository.save(analytics);
        }
    }

    @EventHandler
    public void on(AccountDebitedEvent event) {
        log.info("Analytics - AccountDebitedEvent: {}", event.getId());
        AccountAnalytics analytics = analyticsRepository.findById(event.getId()).orElse(null);
        if (analytics != null) {
            analytics.setTotalDebited(analytics.getTotalDebited() + event.getAmount());
            analytics.setCurrentBalance(analytics.getCurrentBalance() - event.getAmount());
            analytics.setTransactionCount(analytics.getTransactionCount() + 1);
            analyticsRepository.save(analytics);
        }
    }
}
