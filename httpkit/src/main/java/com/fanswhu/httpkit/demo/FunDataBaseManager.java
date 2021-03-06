package com.fanswhu.httpkit.demo;

import com.fanswhu.httpkit.core.BaseDataManager;
import com.fanswhu.httpkit.core.BaseObserver;
import com.fanswhu.httpkit.core.HttpCallBack;


public class FunDataBaseManager extends BaseDataManager<PostRequest, PostInfo> {
    public FunDataBaseManager(HttpCallBack<PostInfo> callBack) {
        super("getJoke", callBack);
    }

    @Override
    protected void setRequestParams(Object... params) {
        PostRequest request = new PostRequest();
        request.setPage((String) params[0]);
        request.setCount((String) params[1]);
        request.setType((String) params[2]);
        doRequest(request);
    }


    public void getData(String page,String count,String type) {
        setRequestParams(page,count,type);
    }
}
