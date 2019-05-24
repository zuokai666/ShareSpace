package xin.ss.controller.person;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xin.ss.model.User;
import xin.ss.service.UserService;
import xin.ss.service.impl.UserServiceImpl;


@WebServlet("/Person/getMsg")
public class getMsg extends HttpServlet {
	
	private static final long serialVersionUID = 7121708302882849132L;
	private UserService service=new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object id=request.getSession().getAttribute("access_id");
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		if(id!=null){
			User s=service.queryUser((int)id);
			if(s!=null){
				response.getWriter().println(s.toJson());
			}else {
				response.getWriter().println("{\"result\":\"0\",\"tip\":\"查无此人\"}");
			}
		}else{
			response.getWriter().println("{\"result\":\"0\",\"tip\":\"查无此人\"}");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}