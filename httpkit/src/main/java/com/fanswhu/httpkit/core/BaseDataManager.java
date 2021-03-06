package com.fanswhu.httpkit.core;

import com.fanswhu.httpkit.bean.BaseRequestParams;
import com.fanswhu.httpkit.bean.BaseResponseParams;
import com.google.gson.Gson;
import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseDataManager<V extends BaseRequestParams, T extends BaseResponseParams> {
    protected String mUrl;
    protected HttpCallBack<T> mCallBack;

    public BaseDataManager(String url, HttpCallBack<T> callBack) {
        this.mCallBack = callBack;
        this.mUrl = url;
    }


    protected abstract void setRequestParams(Object... params);

    /**
     * 设置公共参数
     * @param request 请求实体
     */
    private void setBaseParams(V request) {

    }

    /**
     * 网络请求回调处理
     * @param request 请求实体
     */
    protected void doRequest(V request) {
        setBaseParams(request);
        String s = new Gson().toJson(request);
        Map<String, Object> map = new Gson().<Map<String, Object>>fromJson(s, Map.class);
        getResponse(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<T>(mCallBack));
    }


    /**
     * 发送网络请求
     * @param requestParams 请求参数
     * @return Observable<Object>
     */
    protected Observable<Object> getResponse(Map<String, Object> requestParams) {
        return RetrofitManager.getRetrofitService().executePost(mUrl, requestParams);
    }


}
