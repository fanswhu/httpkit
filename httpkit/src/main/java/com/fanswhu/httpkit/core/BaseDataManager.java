package com.fanswhu.httpkit.core;


import com.fanswhu.httpkit.bean.BaseRequestParams;
import com.fanswhu.httpkit.bean.BaseResponseParams;
import com.google.gson.Gson;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public abstract class BaseDataManager<V extends BaseRequestParams,T extends BaseResponseParams> {
    protected String mUrl;
    protected BaseObserver.HttpCallBack<T> mCallBack;

    public BaseDataManager(String url, BaseObserver.HttpCallBack<T> callBack) {
        this.mCallBack = callBack;
        this.mUrl = url;
    }


    protected abstract void setRequestParams(Object... params);

    private void setBaseParams(V request) {

    }


    protected void doRequest(V request) {
        setBaseParams(request);
        String s = new Gson().toJson(request);
        Map<String,Object> map = new Gson().<Map<String,Object>>fromJson(s, Map.class);
        getResponse(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<T>(mCallBack));
    }

    protected  Observable<String> getResponse(Map<String,Object> requestParams){
      return RetrofitManager.getRetrofitService().executePost(mUrl, requestParams);

    }

}
