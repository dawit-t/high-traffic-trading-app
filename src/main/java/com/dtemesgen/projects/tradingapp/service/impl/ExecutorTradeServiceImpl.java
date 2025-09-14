package com.dtemesgen.projects.tradingapp.service.impl;

import com.dtemesgen.projects.tradingapp.dto.ComparableFutureTask;
import com.dtemesgen.projects.tradingapp.dto.TradeRequest;
import com.dtemesgen.projects.tradingapp.dto.TradeTask;
import com.dtemesgen.projects.tradingapp.model.Trade;
import com.dtemesgen.projects.tradingapp.model.TradeStatus;
import com.dtemesgen.projects.tradingapp.repository.TradeRepository;
import com.dtemesgen.projects.tradingapp.service.TradeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@Primary
@AllArgsConstructor
public class ExecutorTradeServiceImpl implements TradeService {
    private ThreadPoolExecutor executorService;
    private TradeRepository tradeRepository;
    private final ConcurrentHashMap<Long, Trade> tradeCache = new ConcurrentHashMap<>();

    @Override
    public void processTrade(TradeRequest tradeRequest) {
        Runnable tradeExecution = () -> {
            System.out.println("ThreadPoolTaskExecutor pool size: " + executorService.getPoolSize());
            System.out.println("ThreadPoolTaskExecutor number of threads: " + executorService.getActiveCount());
            if (tradeRepository.existsById(tradeRequest.getId())) {
                throw new IllegalArgumentException("Trade with id " + tradeRequest.getId() + " already exists");
            }

            if (tradeRequest.getQuantity() <= 0 || tradeRequest.getPrice() <= 0) {
                throw new IllegalArgumentException("Trade Quantity and Price must be greater than zero");
            }

            tradeCache.put(tradeRequest.getId(), convertToEntity(tradeRequest, TradeStatus.PENDING.name()));
            System.out.println("Processing task: " + Thread.currentThread().getName() + ", priority: " + tradeRequest.getPriority());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            Trade t = convertToEntity(tradeRequest, TradeStatus.APPROVED.name());
            t.setExecutionTime(Instant.now().getEpochSecond());
            tradeRepository.save(t);
            tradeCache.put(tradeRequest.getId(), t);
            System.out.println("Finished processing task: " + Thread.currentThread().getName() + ", priority: " + tradeRequest.getPriority());

            tradeCache.get(tradeRequest.getId());
        };

        executorService.execute(new ComparableFutureTask(new TradeTask(tradeExecution, tradeRequest.getPriority())));
    }

    @Override
    public Trade updateTrade(TradeRequest tradeRequest) {
        Optional<Trade> trade = tradeRepository.findById(tradeRequest.getId());
        if (trade.isPresent()) {
            if (tradeRequest.getQuantity() <= 0 || tradeRequest.getPrice() <= 0) {
                throw new IllegalArgumentException("Trade Quantity and Price must be greater than zero");
            }

            tradeRepository.save(this.convertToEntity(tradeRequest, trade.get().getStatus()));
            tradeCache.put(tradeRequest.getId(), convertToEntity(tradeRequest, trade.get().getStatus()));
        } else {
            throw new IllegalArgumentException("Trade with id " + tradeRequest.getId() + " does not exist");
        }

        return tradeCache.get(tradeRequest.getId());
    }

    @Override
    public Trade getTradeById(Long id) {
        if (tradeCache.containsKey(id)) {
            return tradeCache.get(id);
        }
        return tradeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Trade> getAllTrades() {
        if (tradeCache.size() == tradeRepository.count()) {
            return tradeCache.values().stream().toList();
        }
        return tradeRepository.findAll();
    }
}
