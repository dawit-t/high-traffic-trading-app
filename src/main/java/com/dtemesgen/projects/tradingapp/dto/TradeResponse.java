package com.dtemesgen.projects.tradingapp.dto;

import com.dtemesgen.projects.tradingapp.model.TradeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TradeResponse {
    private TradeStatus status;
    private int priority;
    private Long id;
    private String description;
    private int quantity;
    private double price;
}
