package com.zeroxy.study.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_phone_code")
public class PhoneCode extends StatusDomain implements Serializable{

	private static final long serialVersionUID = -8081313968714267908L;

	private String phone;
	private String code;
	private Long ctime;
	private String areaCode ;
	

	public PhoneCode() {
		this.ctime = System.currentTimeMillis();
	}

	public PhoneCode(String areaCode,String phone, String code) {
		this.areaCode = areaCode;
		this.phone = phone;
		this.code = code;
		this.ctime = System.currentTimeMillis();
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Override
	public String toString() {
		return "PhoneCode [id=" + id + ", phone=" + phone + ", code=" + code
				+ ", ctime=" + ctime + "]";
	}

}
