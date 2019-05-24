package xin.ss.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xin.ss.model.User;
import xin.ss.service.UserService;
import xin.ss.service.impl.UserServiceImpl;
import xin.ss.util.StringMangaer;


/**
 * 更改昵称操作
 */
@WebServlet("/User/updateName")
public class updateName extends HttpServlet {

	private static final long serialVersionUID = 6113520775578427370L;
	private UserService service=new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		Object id=session.getAttribute("id");
		String name=request.getParameter("name");
		if(id!=null){
			if(name.length()>15){
				response.getWriter().println(xin.ss.util.StringMangaer.RESULT_UPDATE_FAIL);
				return;
			}
			User user=new User();
			user.setId((int)id);
			String eString=StringMangaer.getEscapeString(name);
			user.setName(eString);
			boolean isOk=service.updateName(user);
			if(isOk){
				session.setAttribute("name",eString);
				response.getWriter().println(xin.ss.util.StringMangaer.RESULT_UPDATE_SUCCESS);
			}else {
				response.getWriter().println(xin.ss.util.StringMangaer.RESULT_UPDATE_FAIL);
			}
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































