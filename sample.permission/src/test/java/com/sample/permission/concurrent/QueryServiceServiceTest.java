package com.sample.permission.concurrent;

import com.sample.core.exception.UnifiedException;
import com.sample.core.model.dto.QueryRsp;
import com.sample.core.service.ISampleService;
import com.sample.permission.BaseTest;
import com.sample.permission.dto.QueryServiceReq;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by andongxu on 9/19/16.
 */
public class QueryServiceServiceTest extends BaseTest {

    @Autowired
    @Qualifier("queryServiceService")
    private ISampleService<QueryServiceReq, QueryRsp> sampleService;

    @Test
    public void oneThread() throws UnifiedException {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            QueryServiceReq queryServiceReqDto = new QueryServiceReq();
            queryServiceReqDto.setReqId(UUID.randomUUID().toString());
            QueryRsp queryRspDto = sampleService.service(queryServiceReqDto);
            Assert.assertNotNull(queryRspDto);
            Assert.assertTrue(queryRspDto.isSuccess());
        }
        long end = System.currentTimeMillis();

        System.out.println("=====================================>" + (end - begin));
    }

    @Test
    public void multiThread() throws UnifiedException, InterruptedException {
        long begin = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            service.execute(new Runnable() {
//                final int times = i;
                @Override
                public void run() {
                    QueryServiceReq queryServiceReqDto = new QueryServiceReq();
                    queryServiceReqDto.setReqId(UUID.randomUUID().toString());
                    QueryRsp queryRspDto = null;
                    try {
                        queryRspDto = sampleService.service(queryServiceReqDto);
                    } catch (UnifiedException e) {
                        e.printStackTrace();
                    }finally {
                        countDownLatch.countDown();
                    }
                    Assert.assertNotNull(queryRspDto);
                    Assert.assertTrue(queryRspDto.isSuccess());
                }
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();

        System.out.println("=====================================>" + (end - begin));
    }
}
