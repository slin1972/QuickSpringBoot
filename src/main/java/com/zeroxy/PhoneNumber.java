package com.zeroxy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;


public class PhoneNumber implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4896144503296200730L;
	private String areaCode ;
	private String phone ;
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFullPhone(){
		return "+"+areaCode + phone ;
	}
	public PhoneNumber(String areaCode, String phone){
		super();
		try{
			String regEx = "[^0-9]" ;   
			Pattern p = Pattern.compile(regEx);  
			if(!StringUtils.isEmpty(areaCode)){ 
				Matcher m = p.matcher(areaCode);
				this.areaCode = Integer.parseInt(m.replaceAll("").trim())+"";
			}else{
				this.areaCode = "62";
			}
			if(!StringUtils.isEmpty(phone)){
				Matcher m = p.matcher(phone);
				this.phone = Long.parseLong(m.replaceAll("").trim())+"";
				String tempAreacode = this.phone.substring(0,this.areaCode.length());
				if(tempAreacode.equals(this.areaCode)){
					this.phone = this.phone.substring(tempAreacode.length());
				}
			}else{
				throw new PhoneInvalidException("Phone is empty");
			}
		}catch(Exception e){
			throw new PhoneInvalidException("Convert to PhoneNumber exception: areaCode="+areaCode+",phone="+phone,e) ;
		}
		if(phone.length() > 15){
			throw new PhoneInvalidException("Phone length too long, length:"+phone.length() +" more then " + 15 + ".");
		}
	}
	@Override
	public String toString() {
		return "PhoneNumber [areaCode=" + areaCode + ", phone=" + phone + "]";
	}
}
