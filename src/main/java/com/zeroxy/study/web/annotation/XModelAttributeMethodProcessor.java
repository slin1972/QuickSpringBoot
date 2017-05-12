package com.zeroxy.study.web.annotation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zeroxy.ApiBaseParams;
import com.zeroxy.util.JsonUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;


public class XModelAttributeMethodProcessor extends ModelAttributeMethodProcessor{

	public XModelAttributeMethodProcessor(boolean useDefaultResolution) {
		super(useDefaultResolution);
	}
	public XModelAttributeMethodProcessor() {
		super(true);
	}
	@Override
	protected void bindRequestParameters(WebDataBinder binder,
			NativeWebRequest request) {
		HttpServletRequest nativeRequest = (HttpServletRequest) request.getNativeRequest();
		((ExtendedServletRequestDataBinder)binder).bind(nativeRequest);
		//目前只有
		ApiBaseParams apiParams = (ApiBaseParams) binder.getTarget();
		
		try{
			if(apiParams != null){
				apiParams.validate();

				String projectPath = request.getContextPath();
				String otherParams = getOtherParams(request.getParameterMap());
				String ipaddress = getRemoteIp(nativeRequest);
				String apiUrl = nativeRequest.getRequestURI();
				
				if(projectPath != null && projectPath.length() > 1){
					apiUrl = apiUrl.replace(projectPath,"");
				}
				apiParams.setKeyWords(apiUrl);
				apiParams.setOtherParams(otherParams);
				apiParams.setIp(ipaddress);
			}
		}catch(Exception e){
			throw e ;
		}
	}
	private String getOtherParams(Map<String,String[]> parameterMap) {
		Map<String,String[]> map = new HashMap<String, String[]>();
		map.putAll(parameterMap);
		map.remove("av");
		map.remove("et");
		map.remove("os");
		map.remove("m");
		map.remove("dpi");
		map.remove("imei");
		map.remove("imei");
		return JsonUtil.objectToJson(map);
	}
	/**
	 * 获取IP地址。
	 * 
	 * @param request
	 *            请求对象
	 * @return 返回IP地址。
	 */
	private String getRemoteIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			ip = ip.split(",")[0];
		} else {
			ip = request.getHeader("x-real-ip");
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		}
		return ip;
	}
}
