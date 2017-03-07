/*
 * ActionServlet.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.CookieUtil;
import bean.Cart;
import bean.CartItem;
import dao.ComputerDAO;
import entity.Computer;

/**
 * 业务处理逻辑
 * @author LT
 * @version 1.0, 2015年9月21日
 */
public class ActionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		ComputerDAO dao = new ComputerDAO();
		if(path.equals("/list")){
			if(CookieUtil.SECOND)
			System.out.println("改为cookie机制之后的sessionid为："+request.getSession().getId());
			List<Computer> list = dao.findAll();
			request.setAttribute("computers", list);
			request.getRequestDispatcher("computer_list.jsp").forward(request, response);
		}
		else if(path.equals("/buy")){
			long id = Long.parseLong(request.getParameter("id"));
			Computer c = dao.findById(id);
			CartItem item = new CartItem();
			item.setC(c);
			item.setQty(1);
			HttpSession session = request.getSession();
			Cart cart = (Cart)session.getAttribute("cart");
			if(cart==null){
				cart = new Cart();
				if(CookieUtil.SECOND)
				//尝试恢复之前的cookie，恢复之前的商品
				cart.load(CookieUtil.findCookieValue("cart", request));
				session.setAttribute("cart", cart);
			}
			boolean flag = cart.add(item);
			if(flag){//需要交互信息
				request.setAttribute("buy_error"+id, "已经存在");
				request.getRequestDispatcher("list.do").forward(request, response);
			}else{//表示逻辑完结
				if(CookieUtil.SECOND)
				CookieUtil.addCookie(request, response, "cart", cart.getInfo());
				response.sendRedirect("list.do");
			}
		}
		else if(path.equals("/clear")){
			Cart cart = (Cart)request.getSession().getAttribute("cart");
			cart.clear();
			if(CookieUtil.SECOND)
			CookieUtil.deleteCookie(request, response, "cart");
			response.sendRedirect("cart.jsp");
		}
		else if(path.equals("/delete")){
			long id = Long.parseLong(request.getParameter("id"));
			Cart cart = (Cart)request.getSession().getAttribute("cart");
			cart.delete(id);
			if(CookieUtil.SECOND)
			CookieUtil.addCookie(request, response, "cart", cart.getInfo());
			response.sendRedirect("cart.jsp");
		}
		else if(path.equals("/update")){
			long id = Long.parseLong(request.getParameter("id"));
			int qty = Integer.parseInt(request.getParameter("qty"));
			Cart cart = (Cart)request.getSession().getAttribute("cart");
			cart.modify(id, qty);
			if(CookieUtil.SECOND)
			CookieUtil.addCookie(request, response, "cart", cart.getInfo());
			response.sendRedirect("cart.jsp");
		}
	}
}
