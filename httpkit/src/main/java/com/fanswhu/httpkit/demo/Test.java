package com.fanswhu.httpkit.demo;

import com.fanswhu.httpkit.core.BaseObserver;

/**
 * Copyright (C), 2017-2021, 宝能有限公司
 * Author: jayce.feng
 * Date: 2021/3/6
 * Description:
 * version v1.0
 */
public class Test {

    public static void main(String[] args){
        FunDataBaseManager dataBaseManager = new FunDataBaseManager(new BaseObserver.HttpCallBack<PostInfo1>() {
            @Override
            public void onSuccess(PostInfo1 t) {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
        dataBaseManager.getData("1","5","video");
    }
}
