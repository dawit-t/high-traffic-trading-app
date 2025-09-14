package com.dtemesgen.projects.tradingapp.service.impl;

import com.dtemesgen.projects.tradingapp.dto.TradeRequest;
import com.dtemesgen.projects.tradingapp.model.Trade;
import com.dtemesgen.projects.tradingapp.repository.TradeRepository;
import com.dtemesgen.projects.tradingapp.service.TradeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class ReactiveTradeServiceImpl implements TradeService {
    private ThreadPoolExecutor executorService;
    private TradeRepository tradeRepository;
    private final ConcurrentHashMap<Long, Trade> tradeCache = new ConcurrentHashMap<>();

    @Override
    public void processTrade(TradeRequest tradeRequest) {
    }

    @Override
    public Mono<Trade> updateTrade(TradeRequest tradeRequest) {
        return null;
    }

    @Override
    public Mono<Trade> getTradeById(Long id) {
        return null;
    }

    @Override
    public Flux<Trade> getAllTrades() {
        return null;
    }
}
