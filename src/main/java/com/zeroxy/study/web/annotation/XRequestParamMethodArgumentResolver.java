package com.zeroxy.study.web.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;

public class XRequestParamMethodArgumentResolver extends RequestParamMethodArgumentResolver{

	public XRequestParamMethodArgumentResolver(boolean useDefaultResolution) {
		super(useDefaultResolution);
	}
	public XRequestParamMethodArgumentResolver() {
		super(true);
	}
	
	@Override
	protected Object resolveName(String arg0, MethodParameter arg1,
			NativeWebRequest arg2) throws Exception {
		Object o = super.resolveName(arg0, arg1, arg2);
		XRequestParam ann = arg1.getParameterAnnotation(XRequestParam.class);
		if(ann != null){
			if(o != null){
				try{
					if("java.lang.Long".equals(arg1.getParameterType().getName())){	
							Long.valueOf(o.toString());
					}
				}catch(Exception e){
					throw new XRequestParamException(ann.errorCode().getCommonResult());
				}
				return o;
			}
			throw new XRequestParamException(ann.errorCode().getCommonResult());
		}
		return o ;
	}
}
