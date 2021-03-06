package com.fanswhu.httpkit.demo;

import com.fanswhu.httpkit.bean.*;

public class PostRequest extends BaseRequestParams {
    private String page;
    private String count;
    private String type;
    private String key  = "6645f67c9764e4b4dcd88b826ec43379";

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
