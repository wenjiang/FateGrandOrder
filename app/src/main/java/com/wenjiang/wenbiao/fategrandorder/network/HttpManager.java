package com.wenjiang.wenbiao.fategrandorder.network;

import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by wenbiao on 2017/11/9.
 */

public class HttpManager {
    private HashMap queryMap;
    private RequestInterceptor interceptor;
    private String DEBUG_IP;
    private String DEFAULT_IP;
    private static boolean isDebug = false;
    private static HttpManager httpManager;

    private HttpManager() {
        queryMap = new HashMap();
    }

    public static HttpManager getInstance() {
         if(httpManager == null){
             httpManager = new HttpManager();
         }

         return httpManager;
    }

    public void setDefaultIp(String defaultIp){
        this.DEFAULT_IP = defaultIp;
    }

    public void setDebugIp(String debugIp){
        this.DEBUG_IP = debugIp;
    }

    /**
     * 开启Debug开关
     *
     * @param isDebug
     */
    public void debug(boolean isDebug) {
        this.isDebug = isDebug;
    }

    /**
     * 配置请求头
     *
     * @param key
     * @param value
     */
    public void setHeader(final String key, final String value) {
        interceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader(key, value);
            }
        };
    }

    /**
     * 获取对应的请求接口
     *
     * @return
     */
    public <T> T create(Class<T> clazz, RequestInterceptor requestInterceptor) {
        this.interceptor = requestInterceptor;
        if (interceptor != null) {
            String ip = "";
            if (isDebug) {
                ip = DEBUG_IP;
            } else {
                ip = DEFAULT_IP;
            }

            RestAdapter adapter = new RestAdapter.Builder().setConverter(new GsonConverter(new GsonBuilder().serializeNulls().create())).setEndpoint(ip).setRequestInterceptor(interceptor).build();
            return adapter.create(clazz);
        } else {
            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(DEFAULT_IP).build();
            return adapter.create(clazz);
        }

    }

    /**
     * 因为图片上传无法使用Map的键值对形式，所以需要抛出RestAdapter
     *
     * @return
     */
    public RestAdapter getAdapter() {
        String ip = "";
        if (isDebug) {
            ip = DEBUG_IP;
        } else {
            ip = DEFAULT_IP;
        }
        return new RestAdapter.Builder().setEndpoint(ip).build();
    }

    /**
     * 添加GET请求参数
     *
     * @param key
     * @param value
     */
    public void addQueryParam(String key, Object value) {
        queryMap.put(key, value);
    }

    /**
     * 获取请求的参数的集合
     *
     * @return
     */
    public HashMap getQueryMap() {
        return queryMap;
    }

    /**
     * 清除请求的参数的集合
     */
    public void clearParamMap() {
        queryMap.clear();
    }
}
