package com.javapai.thirdparty.haiguan.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		SpringBeanUtils.applicationContext = applicationContext;
	}

}
