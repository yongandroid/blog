package com.xielaoban.util;

/**
 * 封装一个 Http 响应的 Model
 * Created by SeanWu on 2017/3/15.
 */
public class HttpResponseModel {
    private String resString;
    private String resCode;

    public String getResString() {
        return resString;
    }

    public void setResString(String resString) {
        this.resString = resString;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
}
