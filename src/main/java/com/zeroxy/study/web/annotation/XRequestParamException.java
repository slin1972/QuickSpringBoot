package com.zeroxy.study.web.annotation;

import com.zeroxy.CommonResult;

public class XRequestParamException extends RuntimeException {
	
	public XRequestParamException(CommonResult cr) {
		this.cr = cr ;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -7472617185754633364L;

	private CommonResult cr ;

	public CommonResult getCr() {
		return cr;
	}

	public void setCr(CommonResult cr) {
		this.cr = cr;
	}

	@Override
	public String toString() {
		return "CommonResultException [cr=" + cr + "]";
	}
}
