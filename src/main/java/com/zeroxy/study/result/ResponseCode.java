package com.zeroxy.study.result;

import com.zeroxy.CommonResult;

/**
 * Created by SEELE on 2017/5/12.
 */
public class ResponseCode {
    public static final CommonResult OK_0 = newOkResult();
    public static final CommonResult ERROR_999 = new CommonResult(999,"Internal server error.");

    public static CommonResult newOkResult(){
        return new CommonResult(0,"OK");
    }
}
