package com.example.analyticsservice.service;

import com.example.analyticsservice.dtos.AccountAnalyticsResponseDTO;
import com.example.analyticsservice.entities.AccountAnalytics;
import com.example.analyticsservice.repository.AnalyticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnalyticsService {
    private final AnalyticsRepository analyticsRepository;

    public List<AccountAnalyticsResponseDTO> getAllAnalytics() {
        return analyticsRepository.findAll().stream()
                .map(analytics -> new AccountAnalyticsResponseDTO(
                        analytics.getAccountId(),
                        analytics.getTotalCredited(),
                        analytics.getTotalDebited(),
                        analytics.getCurrentBalance(),
                        analytics.getTransactionCount()))
                .toList();
    }

    public AccountAnalyticsResponseDTO getAnalyticsByAccount(String accountId) {
        AccountAnalytics analytics = analyticsRepository.findById(accountId).orElse(null);
        if (analytics == null)
            return null;
        return new AccountAnalyticsResponseDTO(
                analytics.getAccountId(),
                analytics.getTotalCredited(),
                analytics.getTotalDebited(),
                analytics.getCurrentBalance(),
                analytics.getTransactionCount());
    }
}
