package com.dtemesgen.projects.tradingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TradeRequest {
    private int priority;
    private Long id;
    private String description;
    private int quantity;
    private double price;
}
