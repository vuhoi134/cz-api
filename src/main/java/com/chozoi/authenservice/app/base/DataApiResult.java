package com.chozoi.authenservice.app.base;

public class DataApiResult extends BaseApiResult{

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
