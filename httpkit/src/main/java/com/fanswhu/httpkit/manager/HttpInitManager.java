package com.fanswhu.httpkit.manager;

import com.fanswhu.httpkit.constant.Constants;

/**
 * Copyright (C), 2017-2021, 宝能有限公司
 * Author: jayce.feng
 * Date: 2021/3/6
 * Description:
 * version v1.0
 */
public class HttpInitManager {

    private static HttpInitManager instance;
    private HttpInitManager(){

    }

    public static HttpInitManager getInstance(){
        if(instance == null){
            synchronized (HttpInitManager.class){
                if(instance == null){
                    instance = new HttpInitManager();
                }
            }
        }
        return instance;
    }

    public void initBaseUrl(String url){
        Constants.BASE_URL = url;
    }
}
