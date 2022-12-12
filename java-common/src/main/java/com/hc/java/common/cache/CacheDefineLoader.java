package com.hc.java.common.cache;

import cn.hutool.core.io.resource.ResourceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hc.java.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CacheDefineLoader {

    protected static Logger log = LoggerFactory.getLogger(CacheDefineLoader.class);

    public static List<CacheDefine> loadCacheDefine() {
        try {
            String str = ResourceUtil.readUtf8Str("classpath:cache.json");
            return JsonUtils.fromJson(str, new TypeReference<>() {
            });
        } catch (Exception e) {
            log.error("找不到cache.json配置文件");
        }
        return null;
    }
}
