/*
 * CookieUtil.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 服务器使用cookie方式控制
 * @author LT
 * @version 1.0, 2015年10月19日
 */
public class CookieUtil {

	/**
	 * 是的话为第二种方式，服务器控制的cookie机制，推荐使用
	 * 否则的话，是常见的一般session模式，不能防止客户端禁止cookie后的操作
	 */
	public static boolean SECOND = true;
//	public static boolean SECOND = false;
	
	//以下为实现逻辑
//	private static String default_path = "dn07Shopping";//dn07Shopping ，项目改动的话，这个一定要改动
	
	private static int default_age = 360*24*3600;//cookie生存周期
	
	/**
	 * 增加cookie信息
	 */
	public static void addCookie(HttpServletRequest request,HttpServletResponse response,String name,String value){
		try{
			String path = request.getServletContext().getContextPath();//使用动态获取，就不需要
			Cookie cookie = new Cookie(name, 
					URLEncoder.encode(value, "UTF-8"));
			cookie.setMaxAge(default_age);
			cookie.setPath(path);
			response.addCookie(cookie);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 获取cookie的值
	 */
	public static String findCookieValue(String name , HttpServletRequest request){
		String value = null;
		try{
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				for(Cookie cookie:cookies){
					if(cookie.getName().equals(name)){
						value = URLDecoder.decode(cookie.getValue(),"UTF-8");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
	/**
	 * 消除cookie
	 */
	public static void deleteCookie(HttpServletRequest request,HttpServletResponse response,String name){
		Cookie cookie = new Cookie(name, "");
		cookie.setPath(request.getServletContext().getContextPath());
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
