package com.study.liyq.ap.counter;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CounterService {

    private AtomicInteger totalCounter = new AtomicInteger(0);

    private AtomicInteger successCounter = new AtomicInteger(0);

    private AtomicInteger failCounter = new AtomicInteger(0);

    public int incrAndGetTotalCounter(int delta)
    {
        return totalCounter.incrementAndGet();
    }

    public int getTotalCounter()
    {
        return totalCounter.get();
    }

    public int incrAndGetSuccessCounter(int delta)
    {
        return successCounter.incrementAndGet();
    }

    public int getSuccessCounter()
    {
        return successCounter.get();
    }

    public int incrAndGetFailCounter(int delta)
    {
        return failCounter.incrementAndGet();
    }

    public int getFailCounter()
    {
        return failCounter.get();
    }
}
