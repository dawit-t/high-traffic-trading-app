package com.dtemesgen.projects.tradingapp.service;

import com.dtemesgen.projects.tradingapp.dto.TradeRequest;
import com.dtemesgen.projects.tradingapp.dto.TradeResponse;
import com.dtemesgen.projects.tradingapp.model.Trade;
import com.dtemesgen.projects.tradingapp.model.TradeStatus;

public interface TradeService<T> {
    void processTrade(TradeRequest tradeRequest);
    T updateTrade(TradeRequest tradeRequest);
    T getTradeById(Long id);
    T getAllTrades();

    default Trade convertToEntity(TradeRequest tradeRequest, String tradeStatus) {
        return new Trade(
                tradeRequest.getId(),
                tradeRequest.getDescription(),
                tradeRequest.getQuantity(),
                tradeRequest.getPrice(),
                tradeStatus,
                tradeRequest.getPriority(),
                0L
        );
    }

    default TradeResponse convertToResponse(Trade entity) {
        return new TradeResponse(
                entity.getStatus().equals("APPROVED") ? TradeStatus.APPROVED :
                        entity.getStatus().equals("PENDING") ? TradeStatus.PENDING :
                                TradeStatus.CANCELLED,
                entity.getPriority(),
                entity.getId(),
                entity.getDescription(),
                entity.getQuantity(),
                entity.getPrice()
        );
    }
}
