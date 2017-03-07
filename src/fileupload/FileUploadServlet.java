/*
 * FileUploadServlet.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package fileupload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 上传文件功能
 * @author LT
 * @version 1.0, 2015年10月19日
 */
public class FileUploadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//创建一个工厂类的实例，其提供了解析器的缺省配置
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			//FileItem 对象 ，每个表单域所对应的对象
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item:list){//读取表单时区分表单域的类型
				System.out.print("表单域名称："+item.getFieldName());
				if(item.isFormField()){//普通表单域
					System.out.println(" 是普通表单域，值为："+item.getString());
				}else{//文件上传表单域
					String path = getServletContext().getRealPath("upload")+"\\"+item.getName();
					System.out.println(" 是上传表单域，文件的名称为："+item.getName()+" 保存的实际路径为："+path +" 其流对象为："+item.getInputStream());
					item.write(new File(path));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
