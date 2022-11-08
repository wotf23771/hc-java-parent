package com.hc.java.common.utils;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.util.TimeZone;

/**
 * JSON 操作工具类
 *
 * @auth wangxiaolei
 * @date 2021/3/10 0:54
 */
@Slf4j
public class JsonUtils {

    private static final JsonMapper mapper;

    public JsonMapper getMapper() {
        return mapper;
    }

    static {
        mapper = JsonMapper.builder().configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS, true).build();
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    }

    public static void toJson(Object obj, OutputStream os) throws IOException {
        mapper.writeValue(os, obj);
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("转换json字符失败", e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("将json字符转换为对象时失败", e);
        }
    }

    public static <T> T fromJsonForce(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("将json字符转换为对象时失败", e);
        }
    }

    public static <T> T fromJsonForce(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
