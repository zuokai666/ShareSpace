package xin.ss.controller.user;

import java.io.File;
import java.io.IOException;
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

import xin.ss.model.User;
import xin.ss.service.UserService;
import xin.ss.service.impl.UserServiceImpl;
import xin.ss.util.StringMangaer;
import xin.ss.util.UUIDUtil;


/**
 * 更改用户头像操作
 */
@WebServlet("/User/updateUrl")
public class updateUrl extends HttpServlet {

	private static final long serialVersionUID = 8896181212691388191L;
	private UserService service=new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_HTML);
		Object id=session.getAttribute("id");
		if(id!=null){
			User user=new User();
			user.setId((int)id);
			//任务：上传图片，并得到图片url，添加到User对象中去。
			try {
				upload(request,user);
			} catch (Exception e) {
				response.getWriter().println(xin.ss.util.StringMangaer.RESULT_INSERT_FAIL);
				return;
			}
			boolean isOk=service.updateUrl(user);
			if(isOk){
				response.getWriter().println(StringMangaer.RESULT_UPDATE_SUCCESS);
			}else {
				response.getWriter().println(xin.ss.util.StringMangaer.RESULT_UPDATE_FAIL);
			}
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@SuppressWarnings("unchecked")
	private void upload(HttpServletRequest request, User user)  throws Exception {
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
				processFileUpload(item,user);
				break;//只处理一次图片
			}
		}
	}

	private void processFileUpload(FileItem item, User user) throws Exception {
		String pre="/Upload/Image/Title";
		String storeDir=this.getServletContext().getRealPath(pre);
		File realDir=new File(storeDir);
		if(!realDir.exists()){
			realDir.mkdirs();
		}
		String fileName = item.getName();
		if(fileName==""){
			throw new RuntimeException("头像图片为空");
		}
		String suf=fileName.substring(fileName.lastIndexOf("."));
		suf=suf.toLowerCase();
		if(suf.equals(".bmp")||suf.equals(".jpg")||suf.equals(".jpeg")||suf.equals(".png")||suf.equals(".gif")){
			fileName = UUIDUtil.getUUID()+suf;
			//处理把文件放在不同的文件夹下
			String childDirectory = getChildDirectory(realDir,fileName);
			//相对路径+生成的文件名
			String nString=childDirectory+"/"+fileName;
			user.setUrl(pre+"/"+nString);
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





































