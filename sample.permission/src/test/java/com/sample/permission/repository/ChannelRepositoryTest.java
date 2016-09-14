package com.sample.permission.repository;

import com.sample.permission.BaseTest;
import com.sample.permission.model.Channel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by andongxu on 16-8-30.
 */
public class ChannelRepositoryTest extends BaseTest {

    @Autowired
    private ChannelRepository channelRepository;

    @Test
    public void insert0() {
        Channel channel = new Channel();
        channel.setCode("WY");
        channelRepository.save(channel);
    }
    @Test
    public void insert1() {
        Channel channel = new Channel();
        channel.setCode("WX");
        channelRepository.save(channel);
    }
}
