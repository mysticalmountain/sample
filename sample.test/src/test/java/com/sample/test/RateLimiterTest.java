package com.sample.test;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.*;

import java.util.concurrent.*;

/**
 * Created by andongxu on 9/18/16.
 */
public class RateLimiterTest {

    @org.junit.Test
    public void noRateLimiter() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @org.junit.Test
    public void withRateLimiter() throws InterruptedException {
        Long start = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(15);
        final CountDownLatch countDownLatch = new CountDownLatch(100);

        final RateLimiter rateLimiter = RateLimiter.create(10.0); // 每秒不超过10个任务被提交
        final Semaphore semaphore = new Semaphore(50);

        for (int i = 1; i <= 100; i++) {
            final int finalI = i;
//            System.out.println("------>" + i);
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
//                        semaphore.acquire();
//                        rateLimiter.acquire(1);
                        System.out.println(Thread.currentThread() + "\t" + finalI);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
//                        semaphore.release();
                        countDownLatch.countDown();
//                        System.out.println("====" + countDownLatch.getCount());
                    }
                }
            });

        }

        countDownLatch.await();
        Long end = System.currentTimeMillis();
        System.out.println("--------------->" + (end - start));

    }


}
