package com.fanswhu.httpkit.core;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class BaseObserver<T> implements Observer<Object> {
    private static final String TAG = "BaseObserver";
    private final HttpCallBack<T> mHttpCallBack;
    private Disposable mDisposable;

    public BaseObserver(HttpCallBack<T> mHttpCallBack) {
        this.mHttpCallBack = mHttpCallBack;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        this.mDisposable = d;
    }

    @Override
    public void onNext(@NonNull Object o) {

        if (mHttpCallBack != null) {
            try {
                Gson gson = new Gson();
                T response = gson.fromJson(gson.toJson(o),getClazz());
                mHttpCallBack.onSuccess(response);
            } catch (ClassCastException e) {
                Log.e(TAG, "ClassCastException");
            }
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

    public interface HttpCallBack<V> {
        void onSuccess(V t);

        void onError(Throwable e);
    }


    private Class<T> getClazz() {
        Class<T> clazz;
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = null;
        if (superclass instanceof ParameterizedType) {
            parameterizedType = (ParameterizedType) superclass;
            Type[] typeArray = parameterizedType.getActualTypeArguments();
            if (typeArray != null && typeArray.length > 0) {
                clazz = (Class<T>) typeArray[0];
                return clazz;

            }
        }
        return null;
    }
}
