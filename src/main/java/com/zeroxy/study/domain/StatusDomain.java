package com.zeroxy.study.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class StatusDomain extends IdDomain{
	
	protected int status = 1 ;
	public final static int STATUS_DELETE = 0 ;
	public final static int STATUS_NORMAL = 1 ;
	public final static int STATUS_AUDIT = 2 ;
	
	public final static String STATUS_FILED = "status";
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return super.toString()+" ,StatusDomain [status=" + status + "]";
	}
}
