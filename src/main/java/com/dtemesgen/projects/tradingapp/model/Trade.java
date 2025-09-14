package com.dtemesgen.projects.tradingapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Trade {
    @Id
    private Long id;
    private String description;
    private int quantity;
    private double price;
    private String status;
    private int priority;
    private long executionTime;
}

