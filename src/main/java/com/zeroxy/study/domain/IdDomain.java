package com.zeroxy.study.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * ID实体基类
 */
@MappedSuperclass
public abstract class IdDomain{

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	protected Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "IdDomain [id=" + id + "]";
	}
}
