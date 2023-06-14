package com.chandan.hibernateentitymappings.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
public class SpringSchedulerConfig {

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelay() {
        System.out.println(
                "Fixed Delay task scheduleFixedDelay -" + System.currentTimeMillis() / 1000
        );
    }

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayForTaskCompletion() throws InterruptedException {
        System.out.println(
                "Fixed Delay task scheduleFixedDelayForTaskCompletion -" + System.currentTimeMillis() / 1000
        );
        Thread.sleep(2000);
    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRate() {
        System.out.println(
                "Fixed Delay task scheduleFixedRate -" + System.currentTimeMillis() / 1000
        );
    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateForMethodInvocation() throws InterruptedException {
        System.out.println(
                "Fixed Delay task scheduleFixedRateForMethodInvocation -" + System.currentTimeMillis() / 1000
        );
        Thread.sleep(2000);
    }
}
