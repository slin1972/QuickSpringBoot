package com.zeroxy;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SEELE on 2017/5/12.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class  CommonResult implements java.io.Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 9214295854230711421L;
    private int code;
    private String msg;
    private Map<String,Object> data;

    public CommonResult() {
    }

    public CommonResult(int code){
        this.code = code;
    }

    public CommonResult(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public Object getAttribute(String key){
        return data == null?null:data.get(key);
    }

    public CommonResult setAttribute(String key,Object value){
        if(data == null){
            data = new HashMap<>();
        }
        if(value == null){
            data.remove(key);
        }else{
            data.put(key, value);
        }
        return this ;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String,Object> getData() {
        return data;
    }

    public void setData(Map<String,Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
