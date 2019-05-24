package xin.ss.controller.share;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import xin.ss.model.Image;
import xin.ss.model.Share;
import xin.ss.service.ShareService;
import xin.ss.service.impl.ShareServiceImpl;
import xin.ss.util.StringMangaer;
import xin.ss.util.UUIDUtil;


/**
 * 提交分享操作
 */
@WebServlet("/Share/CommitShare")
public class CommitShare extends HttpServlet {
	
	private static final long serialVersionUID = -8408243662172553239L;
	private ShareService service=new ShareServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String result="";
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_HTML);
		Object _id=session.getAttribute("id");
		if(_id!=null){
			Share share=new Share();
			//任务：上传图片，并得到图片url，拼接成Image对象,并得到其它表单信息。
			try {
				upload(request,share);
			} catch (Exception e) {
				response.getWriter().println(xin.ss.util.StringMangaer.RESULT_INSERT_FAIL);
				return;
			}
			share.setUserid((int)_id);
			share.setPublishtime(getTime());
			boolean isOk=service.publishShare(share);
			if(isOk){
				result=xin.ss.util.StringMangaer.RESULT_INSERT_SUCCESS;
			}else{
				result=xin.ss.util.StringMangaer.RESULT_INSERT_FAIL;
			}
			response.getWriter().println(result);
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@SuppressWarnings("unchecked")
	private void upload(HttpServletRequest request, Share share)  throws Exception {
		boolean isMultipartForm = ServletFileUpload.isMultipartContent(request);//Content-Type  multipart/form-data
		if(!isMultipartForm){
			throw new RuntimeException("Your form's Contet-Type is not multipart/form-data.");
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding("utf-8");
		
		fileUpload.setFileSizeMax(1024*1024*3);//设置上传文件的大小不能超过3M
		List<FileItem> fileItems = fileUpload.parseRequest(request);
		List<Image> imageList=new ArrayList<>();
		
		for(FileItem item:fileItems){
			if(!item.isFormField()){
				processFileUpload(item,imageList);
			}else {
				processFormField(item,share);
			}
		}
		share.setImageList(imageList);
	}
	
	private void processFormField(FileItem item, Share share) throws Exception {
		String name = item.getFieldName();
		String value ="";
		try {
			value = item.getString("utf-8");
			if(name.equals("content")){
				if(StringMangaer.isNull(value)){
					throw new RuntimeException();
				}
				share.setContent(StringMangaer.getEscapeString(value));
			}
			if(name.equals("typeid")){
				if(StringMangaer.isNumber(value)){
					share.setTypeid(Integer.parseInt(value));
				}else {
					throw new RuntimeException();
				}
				
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private void processFileUpload(FileItem item, List<Image> imageList) throws Exception {
		String pre="/Upload/Image/Share";
		String storeDir=this.getServletContext().getRealPath(pre);
		File realDir=new File(storeDir);
		if(!realDir.exists()){
			realDir.mkdirs();
		}
		String fileName = item.getName();
		if(fileName==""){
			return;
		}
		String suf=fileName.substring(fileName.lastIndexOf("."));
		suf=suf.toLowerCase();
		if(suf.equals(".bmp")||suf.equals(".jpg")||suf.equals(".jpeg")||suf.equals(".png")||suf.equals(".gif")){
			fileName = UUIDUtil.getUUID()+suf;
			//处理把文件放在不同的文件夹下
			String childDirectory = getChildDirectory(realDir,fileName);
			//相对路径+生成的文件名
			String nString=childDirectory+"/"+fileName;
			Image image=new Image();
			image.setUrl(pre+"/"+nString);
			imageList.add(image);
			try {
				//自动删除临时文件的功能
				item.write(new File(realDir,nString));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			throw new RuntimeException();
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

	private String getTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}





































