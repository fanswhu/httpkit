package com.fanswhu.httpkit.core;

/**
 * Copyright (C), 2017-2021, 宝能有限公司
 * Author: jayce.feng
 * Date: 2021/3/6
 * Description: 网络请求回调
 * version v1.0
 */
public abstract class HttpCallBack<V> {
    public Class<V> cls;

    /**
     * 构造方法里泛型实例化
     */
    public HttpCallBack() {
        cls = TypeUtils.getClazz(getClass(), 0);
    }

    /**
     * 请求成功回调方法
     * @param t 返回实体
     */
    protected abstract void onSuccess(V t);

    /**
     * 请求失败回调方法
     * @param e 失败参数
     */
    protected abstract void onError(Throwable e);
}
