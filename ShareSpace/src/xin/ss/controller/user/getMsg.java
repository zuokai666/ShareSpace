package xin.ss.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xin.ss.model.User;
import xin.ss.service.UserService;
import xin.ss.service.impl.UserServiceImpl;

/**
 * 
 * @author King
 *
 */
@WebServlet("/User/getMsg")
public class getMsg extends HttpServlet {
	
	private static final long serialVersionUID = -3225019289195890583L;
	private UserService service=new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object id=request.getSession().getAttribute("id");
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		if(id!=null){
			User s=service.queryUser((int)id);
			response.getWriter().println(s.toJson());
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}