package com.hc.java.redis.cache;

import com.hc.java.common.cache.CacheDefine;
import com.hc.java.common.cache.CacheDefineLoader;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.*;

/**
 * Redis 缓存自定义配置
 *
 * @auth wangxiaolei
 * @date 2020/11/3 15:08
 */
public class RedisCacheCustomizer implements RedisCacheManagerBuilderCustomizer {

    @Autowired
    private CacheProperties cacheProperties;

    @Override
    public void customize(RedisCacheManager.RedisCacheManagerBuilder builder) {
        List<CacheDefine> cacheDefineList = CacheDefineLoader.loadCacheDefine();
        if (CollectionUtils.isNotEmpty(cacheDefineList)) {
            Set<String> cacheNames = new HashSet<>();
            Map<String, RedisCacheConfiguration> cacheConfigMap = new HashMap<>();
            for (CacheDefine cd : cacheDefineList) {
                cacheNames.add(cd.getName());

                RedisCacheConfiguration cacheConfig = getCacheConfig(cd);
                if (cacheConfig != null) {
                    cacheConfigMap.put(cd.getName(), cacheConfig);
                }
                // 设置缓存名称及缓存配置
                builder.initialCacheNames(cacheNames).withInitialCacheConfigurations(cacheConfigMap);
            }
        }
    }

    public RedisCacheConfiguration getCacheConfig(CacheDefine cacheDefine) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        config = config.entryTtl(Duration.ofSeconds(cacheDefine.getTtl()));
        config = config.prefixCacheNameWith(cacheProperties.getRedis().getKeyPrefix());
        String valueSer = cacheDefine.getValueSerializer();
        if (valueSer != null) {
            if ("java".equals(cacheDefine.getValueSerializer())) {
                config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.java()));
            } else if ("json".equals(cacheDefine.getValueSerializer())) {
                config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JsonRedisSerializer()));
            }
        }
        return config;
    }
}
