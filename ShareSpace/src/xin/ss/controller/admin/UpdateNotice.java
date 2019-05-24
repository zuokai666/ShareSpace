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
import xin.ss.util.TimeGenerator;


@WebServlet("/Admin/UpdateNotice")
public class UpdateNotice extends HttpServlet {

	private static final long serialVersionUID = 3624139293750183170L;
	private NoticeService service=new NoticeServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		String title=request.getParameter("title");
		String id=request.getParameter("id");
		String content=request.getParameter("content");
		String reString="";
		if(id!=null&&title!=null&&content!=null){
			xin.ss.model.Notice notice=new xin.ss.model.Notice();
			notice.setId(Integer.parseInt(id));
			notice.setContent(StringMangaer.getEscapeString(content));
			notice.setTitle(StringMangaer.getEscapeString(title));
			notice.setTime(TimeGenerator.getTime());
			boolean isOk=service.updateNotice(notice);
			if(isOk){
				reString=xin.ss.util.StringMangaer.RESULT_UPDATE_SUCCESS;
			}else{
				reString=xin.ss.util.StringMangaer.RESULT_UPDATE_FAIL;
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





































