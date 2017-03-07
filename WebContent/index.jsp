<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/main/style.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
</script>
</head>
<body>
<h1><a href="list.do">《1.购物车》：展示商品</a></h1>
<br>
<h1>《2.使用监听器和ServletContext实现》：目前连接应用的人数为 <%=application.getAttribute("count") %>
&nbsp;<a href="index-out.jsp"> 退出系统</a></h1>
<br>
<h1>《3.上传》：测试上传图片</h1>
<form action="fileupload" method="post" enctype="multipart/form-data">
	备注：<input type="text" name="ceshi"> &nbsp;
	文件：<input type="file" name="filename">
	<input type="submit" value="提交">
</form>
</body>
</html>
