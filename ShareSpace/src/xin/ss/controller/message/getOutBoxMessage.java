package xin.ss.controller.message;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xin.ss.model.MessageDto;
import xin.ss.service.MessageService;
import xin.ss.service.impl.MessageServiceImpl;
import xin.ss.util.JsonGenerator;


/**
 * 得到发件箱数据
 */
@WebServlet("/Message/getOutBoxMessage")
public class getOutBoxMessage extends HttpServlet {

	private static final long serialVersionUID = 6943349494196601442L;
	private MessageService service=new MessageServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		Object id=session.getAttribute("id");
		if(id!=null){
			List<MessageDto> list=service.queryOutBox((int)id);
			response.getWriter().println(JsonGenerator.getJson(list));
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































