package xin.ss.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xin.ss.service.NoticeService;
import xin.ss.service.impl.NoticeServiceImpl;
import xin.ss.util.StringMangaer;


@WebServlet("/Admin/delNotice")
public class delNotice extends HttpServlet {

	private static final long serialVersionUID = -8054543786908618547L;
	private NoticeService service=new NoticeServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		String id=request.getParameter("id");
		String reString="";
		if(id!=null){
			boolean isOk=service.deleteNotice(Integer.parseInt(id));
			if(isOk){
				reString=xin.ss.util.StringMangaer.RESULT_DELETE_SUCCESS;
			}else{
				reString=xin.ss.util.StringMangaer.RESULT_DELETE_FAIL;
			}
			response.getWriter().println(reString);
		}else {
			response.getWriter().println(StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































