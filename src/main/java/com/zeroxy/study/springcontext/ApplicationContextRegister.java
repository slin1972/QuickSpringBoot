package com.zeroxy.study.springcontext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class ApplicationContextRegister implements ApplicationContextAware {
	 
	public void setApplicationContext(ApplicationContext applicationContext)
	   throws BeansException {
		ContextUtils.setApplicationContext(applicationContext);
	}

}

