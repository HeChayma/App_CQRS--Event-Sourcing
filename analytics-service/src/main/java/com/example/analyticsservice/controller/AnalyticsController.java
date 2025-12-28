package com.example.analyticsservice.controller;

import com.example.analyticsservice.dtos.AccountAnalyticsResponseDTO;
import com.example.analyticsservice.service.AnalyticsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics")
@AllArgsConstructor
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    @GetMapping("/all")
    public List<AccountAnalyticsResponseDTO> getAllAnalytics() {
        return analyticsService.getAllAnalytics();
    }

    @GetMapping("/{accountId}")
    public AccountAnalyticsResponseDTO getAnalyticsByAccount(@PathVariable String accountId) {
        return analyticsService.getAnalyticsByAccount(accountId);
    }
}
