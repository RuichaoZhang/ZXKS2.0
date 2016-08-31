package com.capgemini.util;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
public class WebFormBeanUtils {
	/**
	 * 把请求对象中的参数封装到formbean中
	 * @return 封装好的formbean
	 * @author chao538
	 * @since 2015-11-10
	 * @param request
	 */
	public static <T>T fillFormBean(HttpServletRequest request, Class<T> clazz) {
		try {
			T bean = clazz.newInstance();
			BeanUtils.populate(bean, request.getParameterMap());
			return bean;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}