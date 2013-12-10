package com.qaitdevlabs.qualityassessor.security.customsecurityfilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

/**
 * 
 * @author anujchhabra
 * 
 */
@Component
public class FilterInvocationSecurityMetadataSourcePostProcessor implements
		BeanPostProcessor {
	
	@Autowired
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	/**
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#
	 *      postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	public Object postProcessAfterInitialization(Object bean, String name) {
		System.out.println(bean.getClass().getName());
		if (bean instanceof FilterSecurityInterceptor) {
			((FilterSecurityInterceptor) bean)
					.setSecurityMetadataSource(securityMetadataSource);
		}
		return bean;
	}

	/**
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#
	 *      postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	public Object postProcessBeforeInitialization(Object bean, String name) {
		return bean;
	}

	/**
	 * Setter method for injecting dependency of securityMetadataSource
	 * 
	 * @param securityMetadataSource
	 */
	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

}