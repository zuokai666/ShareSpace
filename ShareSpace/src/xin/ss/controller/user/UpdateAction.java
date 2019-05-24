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
 * 更改用户信息操作
 */
@WebServlet("/User/Update")
public class UpdateAction extends HttpServlet {

	private static final long serialVersionUID = 230065559659031945L;
	private UserService service=new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		Object id=session.getAttribute("id");
		String location=request.getParameter("location");
		String description=request.getParameter("description");
		if(id!=null && location!=null&& description!=null){
			User user=new User();
			user.setId((int)id);
			//未加字数限制，字符转义，错误
			user.setLocation(StringMangaer.getEscapeString(location));
			user.setDescription(StringMangaer.getEscapeString(description));
			boolean isOk=service.update(user);
			if(isOk){
				response.getWriter().println(StringMangaer.RESULT_UPDATE_SUCCESS);
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





































