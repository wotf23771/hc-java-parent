package com.hc.java.common.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

@Service
public class OkHttpClientUtils {

    private OkHttpClient client = new OkHttpClient();

    public OkHttpClient getClient() {
        return client;
    }

    /**
     * post的请求参数，构造RequestBody
     *
     * @param params
     * @return
     */
    public RequestBody createRequestBody(Map<String, String> params) {
        RequestBody body = null;
        okhttp3.FormBody.Builder formEncodingBuilder = new okhttp3.FormBody.Builder();
        if (params != null) {
            Iterator<String> iterator = params.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next();
                formEncodingBuilder.add(key, params.get(key));
            }
        }
        body = formEncodingBuilder.build();
        return body;
    }

    /**
     * 通过GET 请求获得响应流
     *
     * @param url
     * @return
     */
    public InputStream getAsStream(String url) {
        OkHttpClient client = getClient();
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (response.isSuccessful()) {
            return response.body().byteStream();
        }
        return null;
    }

    public String postAsString(String url, Map<String, String> params) {
        String result = null;
        Response response = null;
        try {
            RequestBody body = createRequestBody(params);
            Request request = new Request.Builder()
                    .post(body)
                    .url(url)
                    .build();
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return result;
    }

    public String getAsString(String url) {
        String result = null;
        Response response = null;
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return result;
    }
}
