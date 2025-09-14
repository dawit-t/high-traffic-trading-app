package com.dtemesgen.projects.tradingapp.dto;

import java.util.concurrent.FutureTask;

public class ComparableFutureTask extends FutureTask<Void> implements Comparable<ComparableFutureTask> {
    private final TradeTask task;

    public ComparableFutureTask(TradeTask task) {
        super(task, null);
        this.task = task;
    }

    @Override
    public int compareTo(ComparableFutureTask other) {
        return Integer.compare(this.task.getPriority(), other.task.getPriority());
    }
}