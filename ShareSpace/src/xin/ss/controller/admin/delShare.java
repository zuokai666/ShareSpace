package xin.ss.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xin.ss.model.Image;
import xin.ss.model.Share;
import xin.ss.service.ShareService;
import xin.ss.service.impl.ShareServiceImpl;


/**
 * 管理员删除分享操作
 */
@WebServlet("/Admin/delShare")
public class delShare extends HttpServlet {

	private static final long serialVersionUID = -1089680838709569088L;
	private ShareService service=new ShareServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result="";
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		String shareid=request.getParameter("shareid");
		if(shareid!=null){
			Share share=new Share();
			share.setId(Integer.parseInt(shareid));
			share.setUserid(-1);
			boolean isOk=service.deleteShare(share);
			if(isOk){
				//删除图片
				delImage(ShareServiceImpl.urls);
				result=xin.ss.util.StringMangaer.RESULT_DELETE_SUCCESS;
			}else{
				result=xin.ss.util.StringMangaer.RESULT_DELETE_FAIL;
			}
			response.getWriter().println(result);
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private void delImage(List<Image> urls) {
		if(urls!=null&&urls.size()!=0){
			for(Image i:urls){
				String url=i.getUrl();
				String storeDir=this.getServletContext().getRealPath(url);
				File realDir=new File(storeDir);
				if(realDir.exists()){
					realDir.delete();
				}
			}
		}
		ShareServiceImpl.urls=null;
	}
}





































