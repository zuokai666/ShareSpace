package xin.ss.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xin.ss.service.UserService;
import xin.ss.service.impl.UserServiceImpl;


/**
 * 检测昵称
 */
@WebServlet("/User/CheckName")
public class CheckName extends HttpServlet {

	private static final long serialVersionUID = -6690971712348001020L;
	private UserService service=new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		String name=request.getParameter("name");
		if(name!=null){
			boolean isOk=service.checkName(name);
			if(isOk){
				response.getWriter().println("{\"result\":\"0\",\"tip\":\"昵称已存在\"}");
			}else{
				response.getWriter().println("{\"result\":\"1\",\"tip\":\"昵称通过\"}");
			}
		}else{
			response.getWriter().println("name is null");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































