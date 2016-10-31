package com.sample.kv.jedis;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.internal.BooleanSupplier;
import redis.clients.jedis.Jedis;

/**
 * Created by andongxu on 16-10-28.
 */
public class JedisFactoryTest extends TestCase {

    private JedisFactory jedisFactory;

    public void setUp() {
        JedisConfig jedisConfig = new JedisConfig();
        jedisConfig.setCluster(false);
        jedisConfig.setMasterHost("127.0.0.1");
        jedisConfig.setMasterPort(6379);
        jedisConfig.setPassword("foobared");
        jedisFactory = new JedisFactory(jedisConfig);
    }

    @Test
    public void testConnectMaster() {
        boolean isConnected = jedisFactory.withJedisWork(new JedisFactory.JedisWork<Boolean>() {
            @Override
            public Boolean execute(Jedis jedis) {
                return jedis.isConnected();
            }
        });
        Assert.assertTrue(isConnected);
    }

    @Test
    public void testConnectSentinel() {
        JedisConfig jedisConfig = new JedisConfig();
        jedisConfig.setCluster(true);
        jedisConfig.setPassword("foobared");
        jedisConfig.setMasterName("mymaster");
        jedisConfig.setSentinelHost("127.0.0.1:26379,127.0.0.1:26389,127.0.0.1:26399");
        jedisFactory = new JedisFactory(jedisConfig);
        boolean isConnected = jedisFactory.withJedisWork(new JedisFactory.JedisWork<Boolean>() {
            @Override
            public Boolean execute(Jedis jedis) {
                return jedis.isConnected();
            }
        });
    }

}
