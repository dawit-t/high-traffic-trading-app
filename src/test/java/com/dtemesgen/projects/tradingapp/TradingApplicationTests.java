package com.dtemesgen.projects.tradingapp;

import com.dtemesgen.projects.tradingapp.dto.TradeRequest;
import com.dtemesgen.projects.tradingapp.service.TradeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ThreadPoolExecutor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class TradingApplicationTests {
    @Autowired
    private TradeService service;
    @Mock
    private ThreadPoolExecutor executorMock;

    @Test
    void testAllTradeRequestsExecuted_Success() {
        service.processTrade(new TradeRequest(3, 1L, "Trade 3", 100, 10.5));
        service.processTrade(new TradeRequest(2, 2L, "Trade 2", 125, 11.5));
        service.processTrade(new TradeRequest(1, 3L, "Trade 1", 150, 12.5));

        verify(executorMock, times(3)).execute(any(Runnable.class));
    }

    @Test
    void testTradesTasksExecuted_BasedOnPriorityOrder() {

    }


    @Test
    void testInvalidTrade_ThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.processTrade(new TradeRequest(1, 1L, "Faulty Trade", -100, 10.5));
        });
    }
}

//https://blog.devops.dev/multithreading-executorservice-in-spring-2e4e1fb5de11
//https://nurkiewicz.com/2014/11/executorservice-10-tips-and-tricks.html
//https://medium.com/@lakshyachampion/a-comprehensive-guide-to-multithreading-and-concurrency-in-java-9bf8a0a0bb82
//https://medium.com/@reetesh043/master-java-executorservice-for-high-performance-concurrency-0a15f100a889
//https://medium.com/@3eid/deep-dive-into-java-memory-management-heap-stack-metaspace-and-garbage-collection-df6548fe6860
//https://medium.com/@AlexanderObregon/javas-executorservice-awaittermination-method-explained-76cc9a32cffd
//https://jvmaware.com/priority-queue-and-threadpool/