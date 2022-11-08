package com.hc.java.common.cache;

import java.util.concurrent.TimeUnit;

public class CacheDefine {

    private String name;

    /**
     * 默认缓存超时时间为 24 小时
     */
    private long ttl = TimeUnit.HOURS.toMinutes(24);

    private String valueSerializer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public String getValueSerializer() {
        return valueSerializer;
    }

    public void setValueSerializer(String valueSerializer) {
        this.valueSerializer = valueSerializer;
    }
}
