/*
 * listenerTest.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听器demo
 * @author LT
 * @version 1.0, 2015年10月16日
 */
public class ListenerTest implements HttpSessionListener{
	private int count = 0;
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		ServletContext context =  event.getSession().getServletContext();
		System.out.println("ServletContext的初始化参数 version："+context.getInitParameter("version"));
		System.out.println("ServletContext的求取WEB-INF路径 ：" + context.getRealPath("WEB-INF"));
		count++;
		context.setAttribute("count", count);
		System.out.println("session创建，人数加一");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		count--;
		event.getSession().getServletContext().setAttribute("count", count);
		System.out.println("session销毁，人数减一");
	}
}
