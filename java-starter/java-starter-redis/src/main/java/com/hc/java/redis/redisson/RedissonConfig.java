package com.hc.java.redis.redisson;

import com.hc.java.common.spring.SpringApplicationContext;
import org.redisson.Redisson;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Redisson 配置类
 *
 * @author wangxiaolei
 * @since 2022/7/18 11:46
 */
public class RedissonConfig {

    private static final String REDIS_PROTOCOL_PREFIX = "redis://";

    private static final String REDISS_PROTOCOL_PREFIX = "rediss://";

    @Value("${spring.redis.prefix}")
    private String redisPrefix;

    @Autowired
    private RedisProperties redisProperties;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        Config config = null;
        Method clusterMethod = ReflectionUtils.findMethod(RedisProperties.class, "getCluster");
        Method timeoutMethod = ReflectionUtils.findMethod(RedisProperties.class, "getTimeout");
        Object timeoutValue = ReflectionUtils.invokeMethod(timeoutMethod, redisProperties);
        int timeout;
        if (null == timeoutValue) {
            timeout = 10000;
        } else if (!(timeoutValue instanceof Integer)) {
            Method millisMethod = ReflectionUtils.findMethod(timeoutValue.getClass(), "toMillis");
            timeout = ((Long) ReflectionUtils.invokeMethod(millisMethod, timeoutValue)).intValue();
        } else {
            timeout = (Integer) timeoutValue;
        }

        config = new Config();
        String prefix = REDIS_PROTOCOL_PREFIX;
        Method method = ReflectionUtils.findMethod(RedisProperties.class, "isSsl");
        if (method != null && (Boolean) ReflectionUtils.invokeMethod(method, redisProperties)) {
            prefix = REDISS_PROTOCOL_PREFIX;
        }

        config.useSingleServer()
                .setAddress(prefix + redisProperties.getHost() + ":" + redisProperties.getPort())
                .setConnectTimeout(timeout)
                .setDatabase(redisProperties.getDatabase())
                .setPassword(redisProperties.getPassword());
        return Redisson.create(config);
    }

    /**
     * 获得信号量
     *
     * @param key          信号量 KEY
     * @param maxSemaphore 信号量数量
     * @return
     */
    public RSemaphore getSemaphore(String key, int maxSemaphore) {
        RedissonClient redissonClient = SpringApplicationContext.getBean(RedissonClient.class);
        RSemaphore semaphore = redissonClient.getSemaphore(redisPrefix + key);
        semaphore.trySetPermits(maxSemaphore);
        return semaphore;
    }
}
