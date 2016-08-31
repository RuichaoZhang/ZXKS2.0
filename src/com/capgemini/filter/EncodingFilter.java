package com.capgemini.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 定义一个过滤器,作用是权限拦截和规定字符编码
 * @author chao538
 */
public class EncodingFilter implements Filter {
	private String charSet = "UTF-8";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		charSet = filterConfig.getInitParameter("charSet");
	}
	@Override
	public void doFilter(ServletRequest sreq, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*
		Map<String, String[]> map = request.getParameterMap();
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String key = it.next();
			String[] args = map.get(key);
			String[] temp = new String[args.length];
			for(int i=0;i<args.length;i++)
			{
				String str = new String(args[i].getBytes("ISO8859-1"), "UTF-8");
				temp[i] = str;
				System.out.println(str);
			}
			map.put(key, temp);
		}
		*/
		
		HttpServletRequest request = (HttpServletRequest)sreq;
			request.setCharacterEncoding(charSet);
			response.setContentType("text/html; charset=utf-8");
			chain.doFilter(request, response);	
	}
	@Override
	public void destroy() {
		
	}
}
