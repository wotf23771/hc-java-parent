package com.hc.java.redis;

import com.hc.java.redis.cache.RedisCacheCustomizer;
import com.hc.java.redis.redisson.RedissonConfig;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

/**
 * Redis 自动配置
 *
 * @author wangxiaolei
 * @since 2022/11/6 23:48
 */
public class RedisAutoConfigure {

    @Bean
    public RedisCacheCustomizer redisCacheCustomizer() {
        return new RedisCacheCustomizer();
    }

    @ConditionalOnClass(RedissonClient.class)
    @Bean
    public RedissonConfig redissonConfig() {
        return new RedissonConfig();
    }
}
