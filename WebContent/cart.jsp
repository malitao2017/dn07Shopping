<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.*,bean.*,entity.*,util.CookieUtil" %>
<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<link href="css/main/style.css" type="text/css" rel="stylesheet">
	<title>购物车操作</title>
</head>
<body topmargin="10">
	<div id="append_parent"></div>
	<table cellspacing="6" cellpadding="2" width="100%" border="0">
	<tbody>
	<tr>
	<td>
			<table class="guide" cellspacing="0" cellpadding="0" border="0" width="100%">
			<tbody><tr><td>
			位置:&nbsp;&nbsp;<a href="#">主页</a>&nbsp;/&nbsp;<a href="list.do">展示列表</a>&nbsp;/&nbsp;购物车
			</td></tr></tbody>
			</table>
				<br/>
			<table class="tableborder" width="100%" cellpadding="0" cellspacing="0" border="0">
			<tbody><tr class="header"><td class="altbg2" colspan="6">购物车信息</td></tr></tbody>
			<tbody><tr>
			<td class="altbg1" width="10%">名称</td><td class="altbg1" width="20%">图片</td>
			<td class="altbg1" width="10%">价格</td><td class="altbg1" width="20%">描述</td>
			<td class="altbg1" width="10%">数量</td><td class="altbg1">操作</td></tr></tbody>
			<tbody>
				<%
				Cart cart = (Cart)session.getAttribute("cart");
				if(cart==null){
					if(CookieUtil.SECOND){
					cart = new Cart();
					cart.load(CookieUtil.findCookieValue("cart", request));
					session.setAttribute("cart", cart);
					}else{
					%>
					<tr><td align="center" class="altbg2" colspan="6" ><span style="color:red;">购物车为空</span></td></tr>
					<%}
				}
				if(cart!=null && cart.list().size()>0){
					List<CartItem> items =  cart.list();
					for(int i=0;i<items.size();i++){
						CartItem item = items.get(i);
						Computer c = item.getC();
						int qty = item.getQty();
						%>
							<tr>
								<td class="altbg2"><%=c.getModel() %></td>
								<td class="altbg2"><img src="img/d007/<%=c.getPic() %>" width="150" height="90"></td>
								<td class="altbg2"><%=c.getPrice() %></td>
								<td class="altbg2"><%=c.getProdDesc() %></td>
			 					<td class="altbg2"><%= qty %></td>
								<td class="altbg2">
									<input type="text" size="3" id="qty_<%=c.getId()%>" value="<%= qty %>" />
									<a href="javascript:;" onclick="location='update.do?id=<%=c.getId()%>&qty='+document.getElementById('qty_<%=c.getId()%>').value">更改数量</a>
									<a href="delete.do?id=<%=c.getId()%>">删除该项目</a>
								</td>
							</tr>
						<%
					}%>
					<tr><td class="altbg1" colspan="6">总价值：￥ <%=cart.total() %></td></tr>
					<%
				}
				%>
			</tbody>
			</table>	
						<br/>
			<center>
				<input class="button" type="button" onclick="location='list.do';" value="返回展示页面">	
				<input class="button" type="button" onclick="location='clear.do';" value="清空数据">	
			</center>
	</td>
	</tr>
	</tbody>
	</table>
</body>
</html>