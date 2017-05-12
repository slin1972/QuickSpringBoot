package com.zeroxy;

import com.zeroxy.study.result.ErrorCode;
import com.zeroxy.study.web.annotation.XRequestParamException;
import org.apache.commons.lang3.StringUtils;




public class ApiBaseParams implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5620683303762570557L;
	private String av;
	private String et;
	private String os;
	private String m;
	private String dpi;
	private String imei;
	private String keyWords;
	private String token ;
	private String otherParams ;
	private String ip ;
	public String getAv() {
		return av;
	}
	public void setAv(String av) {
		this.av = av;
	}
	public String getEt() {
		return et;
	}
	public void setEt(String et) {
		this.et = et;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getM() {
		return m;
	}
	public void setM(String m) {
		this.m = m;
	}
	public String getDpi() {
		return dpi;
	}
	public void setDpi(String dpi) {
		this.dpi = dpi;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getOtherParams() {
		return otherParams;
	}
	public void setOtherParams(String otherParams) {
		this.otherParams = otherParams;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public ApiBaseParams(String av, String et, String os, String m, String dpi,
			String imei) {
		super();
		this.av = av;
		this.et = et;
		this.os = os;
		this.m = m;
		this.dpi = dpi;
		this.imei = imei;
	}
	
	public ApiBaseParams() {
		super();
	}
	public void validate() {
		if(StringUtils.isBlank(av)||StringUtils.isBlank(et)||StringUtils.isBlank(os)||StringUtils.isBlank(m)||StringUtils.isBlank(dpi)||StringUtils.isBlank(imei)){
			CommonResult commonResult = ErrorCode.ERROR_302.getCommonResult();
			throw new XRequestParamException(commonResult);
		}
	}
}
