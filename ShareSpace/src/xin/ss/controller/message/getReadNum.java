package xin.ss.controller.message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xin.ss.service.MessageService;
import xin.ss.service.impl.MessageServiceImpl;


/**
 * 得到未阅读数目
 */
@WebServlet("/Message/getReadNum")
public class getReadNum extends HttpServlet {

	private static final long serialVersionUID = 3494100478756159851L;
	private MessageService service=new MessageServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		Object id=session.getAttribute("id");
		if(id!=null){
			int list=service.getRead((int)id);
			response.getWriter().println("{\"result\":\"1\",\"tip\":\""+list+"\"}");
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































