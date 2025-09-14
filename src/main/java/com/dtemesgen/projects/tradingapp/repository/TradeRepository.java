package com.dtemesgen.projects.tradingapp.repository;

import com.dtemesgen.projects.tradingapp.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
}
