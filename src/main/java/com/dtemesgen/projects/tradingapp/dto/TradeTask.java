package com.dtemesgen.projects.tradingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TradeTask implements Runnable {
    private final Runnable task;
    @Getter
    private final int priority;

    @Override
    public void run() {
        task.run();
    }
}