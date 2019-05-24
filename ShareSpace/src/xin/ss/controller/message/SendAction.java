package xin.ss.controller.message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xin.ss.model.Message;
import xin.ss.service.MessageService;
import xin.ss.service.UserService;
import xin.ss.service.impl.MessageServiceImpl;
import xin.ss.service.impl.UserServiceImpl;
import xin.ss.util.StringMangaer;
import xin.ss.util.TimeGenerator;


/**
 * 发送信息
 */
@WebServlet("/Message/SendAction")
public class SendAction extends HttpServlet {

	private static final long serialVersionUID = 7828231345678645321L;
	private MessageService service=new MessageServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		Object id=session.getAttribute("id");
		String name=request.getParameter("name");
		String content=request.getParameter("content");
		if(id!=null&&content!=null&&name!=null){
			UserService s=new UserServiceImpl();
			int code=s.queryIdByName(name);
			if(code==-1){
				response.getWriter().println("{\"result\":\"0\",\"tip\":\"查无此人\"}");
				return;
			}else if(code==(int)id){
				response.getWriter().println("{\"result\":\"0\",\"tip\":\"不能给自己发信息\"}");
				return;
			}
			Message m=new Message();
			m.setContent(StringMangaer.getEscapeString(content));
			m.setGuestid((int)id);
			m.setHostid(code);
			m.setTime(TimeGenerator.getTime());
			boolean isOk=service.sendMsg(m);
			if(isOk){
				response.getWriter().println("{\"result\":\"1\",\"tip\":\"发送成功\"}");
			}else {
				response.getWriter().println("{\"result\":\"0\",\"tip\":\"发送失败\"}");
			}
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































