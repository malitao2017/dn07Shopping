http://127.0.0.1:8080/dn07Shopping

1.servlet-购物车行为，思路是操作类放到session当中

第二种购物车方案：使用cookie机制解决浏览器关闭购物车信息消失问题
入口开关是：util.CookieUtil.SECOND ，true则为第二种方案，false为第一种常规方案

2.过滤器：进行处理更改电脑数量的问题
filter.FilterTest 方法实现购物车的更新操作-更改商品的数量
过滤点是 /update.do

3.监听器：使用应用容器ServletContext ,继承的是HttpSessionListener 处理session的开关时的动作控制
listener.ListenerTest 放入应用容器中，每个浏览器的访问的时候显示当前在线人数
1)实现访问的链接统计
2)登陆和session退出

4.上传文件
路径为：  fileupload.FileUploadServlet
使用jar包：commons-fileupload-1.2.1.jar 和 commons-io-1.4.jar 
核心其中类可以对象化表单中的每个对象域如每个<input>，根据其类型可判断出是否是文件，得到其流、文件名、域名

web.xml中的配置顺序有先后顺序的要求，为：
ServletContext（应用的容器） ：<context-param>
listener （监听器）：<listener>
filter （过滤器）:<filter>
servlet ：<servlet>












