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
 * 更改密码操作
 */
@WebServlet("/User/updatePassword")
public class updatePassword extends HttpServlet {

	private static final long serialVersionUID = 9041079067420721848L;
	private UserService service=new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		Object id=session.getAttribute("id");
		String password=request.getParameter("password");
		if(id!=null && password!=null){
			if(StringMangaer.pwdIsWrong(password)){
				response.getWriter().println(xin.ss.util.StringMangaer.RESULT_UPDATE_FAIL);
				return;
			}
			User user=new User();
			user.setId((int)id);
			user.setPassword(password);
			boolean isOk=service.updatePsw(user);
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





































