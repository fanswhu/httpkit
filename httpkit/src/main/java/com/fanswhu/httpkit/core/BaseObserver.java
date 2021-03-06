package com.fanswhu.httpkit.core;

import com.google.gson.Gson;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 网络请求回调包装类
 * @param <V> 返回泛型
 */
public class BaseObserver<V> implements Observer<Object> {
    private static final String TAG = "BaseObserver";
    private final HttpCallBack<V> mHttpCallBack;
    private Disposable mDisposable;

    public BaseObserver(HttpCallBack<V> mHttpCallBack) {
        this.mHttpCallBack = mHttpCallBack;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        this.mDisposable = d;
    }

    @Override
    public void onNext(@NonNull Object o) {
        if (mHttpCallBack != null) {
            Gson gson = new Gson();
            String str = gson.toJson(o);
            V response = gson.fromJson(str, mHttpCallBack.cls);
            mHttpCallBack.onSuccess(response);
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (mHttpCallBack != null) {
            mHttpCallBack.onError(e);
        }
        disposableObserver();
    }

    @Override
    public void onComplete() {
        disposableObserver();

    }

    private void disposableObserver() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }


}
