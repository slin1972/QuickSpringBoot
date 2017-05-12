package com.zeroxy.study.result;

import com.zeroxy.CommonResult;

/**
 * Created by SEELE on 2017/5/12.
 */
public class ResponseCode {
    public static CommonResult newOkResult(){
        return new CommonResult(0,"OK");
    }
}
