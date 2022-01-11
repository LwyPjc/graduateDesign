package com.graduation.education.util;

import org.apache.commons.lang3.StringUtils;

public class ResultMap {
    private static final long serialVersionUID = 7939790343084283699L;
    public static final String CODE_SUCCESS = "200";
    public static final String CODE_ERROR = "500";

    private String status;
    private Object msg;

    public ResultMap(){
        this.status = CODE_SUCCESS;
        this.msg = StringUtils.EMPTY;
    }

    public ResultMap setSuccss(Object msg){
        this.status = CODE_SUCCESS;
        this.msg = msg;
        return this;
    }

    public ResultMap setResult(String status, Object msg){
        this.status = status;
        this.msg = msg;
        return this;
    }

    public ResultMap setError(Object msg){
        this.status = CODE_ERROR;
        this.msg = msg;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

}
