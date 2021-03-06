package com.fanswhu.httpkit.core;

import android.util.Log;

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
                T response = (T) o;
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

}
