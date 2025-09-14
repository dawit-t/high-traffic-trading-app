package com.dtemesgen.projects.tradingapp.controller;

import com.dtemesgen.projects.tradingapp.dto.TradeRequest;
import com.dtemesgen.projects.tradingapp.model.Trade;
import com.dtemesgen.projects.tradingapp.service.TradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trades")
public class TradeController {
    private final TradeService tradeService;

    @PostMapping("/execute")
    public void executeTrade(@RequestBody TradeRequest request) {
        tradeService.processTrade(request);
    }

    @PutMapping("/update")
    public Trade updateTrade(@RequestBody TradeRequest request) {
        return (Trade) tradeService.updateTrade(request);
    }

    @GetMapping("/{id}")
    public Trade getTradeById(@PathVariable Long id) {
        return (Trade) tradeService.getTradeById(id);
    }

    @GetMapping()
    public List<Trade> getAllTrades() {
        return (List<Trade>) tradeService.getAllTrades();
    }
}
