package com.sample.test.module.user;

import com.sample.test.module.IProcessor;

/**
 * Created by andongxu on 16-8-12.
 */
public class CommonReversal<RES> implements IReversal<String, RES> {

    @Override
    public RES reversal(String req, IProcessor<String, RES> processor, Object... args) throws Exception {

        //TODO 1. 查询交易流水；2. 组装报文；3. 更新交易状态；4. 发送报文；5. 更新交易状态
        //查询交易流水
        //相关交易检查
        //组织冲正请求数据
        //更新交易流水状态
        String message = "";
        //冲正
        processor.processor(req, message);
        //更新交易状态

        return null;
    }

    @Override
    public void init() throws Throwable {


    }

    @Override
    public void destory() throws Throwable {

    }
}
