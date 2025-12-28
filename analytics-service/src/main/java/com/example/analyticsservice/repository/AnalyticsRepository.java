package com.example.analyticsservice.repository;

import com.example.analyticsservice.entities.AccountAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyticsRepository extends JpaRepository<AccountAnalytics, String> {
}
