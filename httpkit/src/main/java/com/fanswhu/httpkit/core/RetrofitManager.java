package com.fanswhu.httpkit.core;

import android.util.Log;

import com.fanswhu.httpkit.constant.Constants;
import com.fanswhu.httpkit.manager.CustomInterceptorManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    public static Retrofit mRetrofit;

    public synchronized static RetrofitService getRetrofitService() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                    .build();
        }
        return mRetrofit.create(RetrofitService.class);
    }


    /**
     * 获取OkHttpClient
     * 用于打印请求参数
     *
     * @return OkHttpClient
     */
    public static OkHttpClient getOkHttpClient() {
        // 日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        // 新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("Http请求参数：", message);
            }
        });
        loggingInterceptor.setLevel(level);
        // 定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        // OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        httpClientBuilder.addInterceptor(new CommonInterceptor());
        // 添加自定义拦截器
        for (Interceptor value : CustomInterceptorManager.getInstance().getInterceptors()) {
            httpClientBuilder.addInterceptor(value);
        }
        return httpClientBuilder.build();
    }
}
