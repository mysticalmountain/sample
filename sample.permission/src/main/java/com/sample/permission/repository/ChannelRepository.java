package com.sample.permission.repository;

import com.sample.permission.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 16-8-29.
 */
@Component
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    public Channel findByCode(String code);
}
