package com.hc.java.common.cache;

import com.hc.java.common.spring.SpringApplicationContext;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * 缓存工厂
 *
 * @author wangxiaolei
 * @since 2021/3/18 12:56
 */
public class CacheFactory {

    /**
     * 根据缓存名称获得缓存操作对象
     *
     * @param cacheName 缓存名称
     * @return 缓存操作对象
     */
    public static Cache getCache(String cacheName) {
        return SpringApplicationContext.getBean(CacheManager.class).getCache(cacheName);
    }

    /**
     * 根据缓存名称、缓存 KEY 获得缓存数据
     *
     * @param cacheName 缓存名称
     * @param key       缓存 KEY
     * @param <T>       返回数据类型，进行强制转换
     * @return 缓存数据
     */
    public static <T> T getCacheValue(String cacheName, String key) {
        Cache cache = getCache(cacheName);
        Cache.ValueWrapper vw = cache.get(key);
        if (vw != null) {
            return (T) vw.get();
        }
        return null;
    }
}
