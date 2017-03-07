/*
 * Filter.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器demo
 * 条件： /update.do
 * @author LT
 * @version 1.0, 2015年10月12日
 */
public class FilterTest implements Filter{

	@Override
	public void destroy() {
		System.out.println("过滤器销毁：destrory");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		try{
			int qty = Integer.parseInt(request.getParameter("qty"));
			System.out.println("更改的数量为："+qty);
			chain.doFilter(arg0, arg1);
		}catch(Exception e ){
			((HttpServletResponse)arg1).setContentType("text/html;charset=utf-8;");
			((HttpServletResponse)arg1).getWriter().println("不是整数格式！");
		}
	}
	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("过滤器初始化：init");
		String value = config.getInitParameter("param");
		System.out.println("初始化参数为：" + value);
	}
}


