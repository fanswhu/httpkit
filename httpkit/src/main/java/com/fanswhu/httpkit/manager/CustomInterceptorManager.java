package com.fanswhu.httpkit.manager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

/**
 * Copyright (C), 2017-2021, 宝能有限公司
 * Author: jayce.feng
 * Date: 2021/3/6
 * Description: 自定义拦截器
 * version v1.0
 */
public class CustomInterceptorManager {
    private static CustomInterceptorManager mCustomInterceptorManager;
    private static final List<Interceptor> list = new ArrayList<>();

    private CustomInterceptorManager() {

    }

    public static CustomInterceptorManager getInstance() {
        if (mCustomInterceptorManager == null) {
            synchronized (CustomInterceptorManager.class) {
                if (mCustomInterceptorManager == null) {
                    mCustomInterceptorManager = new CustomInterceptorManager();
                }
            }
        }
        return mCustomInterceptorManager;
    }



    public CustomInterceptorManager addInterceptor(Interceptor interceptor) {
        list.add(interceptor);
        return mCustomInterceptorManager;
    }

    public List<Interceptor> getInterceptors() {
        return list;
    }
}
