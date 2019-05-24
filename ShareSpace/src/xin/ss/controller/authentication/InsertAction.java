package xin.ss.controller.authentication;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import xin.ss.model.Authentication;
import xin.ss.service.AuthenticationService;
import xin.ss.service.impl.AuthenticationServiceImpl;
import xin.ss.util.StringMangaer;
import xin.ss.util.TimeGenerator;
import xin.ss.util.UUIDUtil;


@WebServlet("/Authentication/InsertAction")
public class InsertAction extends HttpServlet {

	private static final long serialVersionUID = -6030838921164110755L;
	private AuthenticationService service=new AuthenticationServiceImpl();
	
	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_HTML);
		Object id=session.getAttribute("id");
		if(id!=null){
			Authentication auth=new Authentication();
			auth.setUserid((int)id);
			auth.setTime(TimeGenerator.getTime());
			auth.setStatus(service.I_SUBMIT);
			auth.setResult("用户已提交，等待受理中");
			try {
				upload(request,auth);
			} catch (Exception e) {
				response.getWriter().println(xin.ss.util.StringMangaer.RESULT_INSERT_FAIL);
				return;
			}
			boolean isOk=service.submitIdentity(auth);
			if(isOk){
				response.getWriter().println(StringMangaer.RESULT_INSERT_SUCCESS);
			}else {
				response.getWriter().println(xin.ss.util.StringMangaer.RESULT_INSERT_FAIL);
			}
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@SuppressWarnings("unchecked")
	private void upload(HttpServletRequest request,Authentication auth)  throws Exception {
		boolean isMultipartForm = ServletFileUpload.isMultipartContent(request);//Content-Type  multipart/form-data
		if(!isMultipartForm){
			throw new RuntimeException("Your form's Contet-Type is not multipart/form-data.");
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding("utf-8");
		
		fileUpload.setFileSizeMax(1024*1024*3);//设置上传文件的大小不能超过3M
		List<FileItem> fileItems = fileUpload.parseRequest(request);
		
		for(FileItem item:fileItems){
			if(!item.isFormField()){
				processFileUpload(item,auth);
			}else {
				processFormField(item,auth);
			}
		}
	}
	private void processFormField(FileItem item,Authentication auth) throws Exception {
		String name = item.getFieldName();
		String value ="";
		try {
			value = item.getString("utf-8");
			if(name.equals("content")){
				if(StringMangaer.isNull(value)){
					throw new RuntimeException();
				}
				auth.setContent(StringMangaer.getEscapeString(value));
			}
			if(name.equals("title")){
				if(StringMangaer.isNull(value)){
					throw new RuntimeException();
				}
				auth.setTitle(value);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	private void processFileUpload(FileItem item,Authentication auth) throws Exception {
		String pre="/Upload/Image/Authentication";
		String storeDir=this.getServletContext().getRealPath(pre);
		File realDir=new File(storeDir);
		if(!realDir.exists()){
			realDir.mkdirs();
		}
		String fileName = item.getName();
		if(fileName==""){
			throw new RuntimeException("图片为空");
		}
		String suf=fileName.substring(fileName.lastIndexOf("."));
		suf=suf.toLowerCase();
		if(suf.equals(".bmp")||suf.equals(".jpg")||suf.equals(".jpeg")||suf.equals(".png")||suf.equals(".gif")){
			fileName = UUIDUtil.getUUID()+suf;
			//处理把文件放在不同的文件夹下
			String childDirectory = getChildDirectory(realDir,fileName);
			//相对路径+生成的文件名
			String nString=childDirectory+"/"+fileName;
			auth.setUrl(pre+"/"+nString);
			try {
				//自动删除临时文件的功能
				item.write(new File(realDir,nString));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			throw new RuntimeException("文件不是图片");
		}
	}
	//创建子目录:用哈希值    
	private String getChildDirectory(File realDirectory, String fileName) {
		int hashCode = fileName.hashCode();
		String code = Integer.toHexString(hashCode);//把哈希值转成16进制
		String childDirectory = code.charAt(0)+"/"+code.charAt(1);
		File dir = new File(realDirectory,childDirectory);
		if(!dir.exists())
			dir.mkdirs();
		return childDirectory;
	}
}





































