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
 * 检测账号
 */
@WebServlet("/User/CheckAccount")
public class CheckAccount extends HttpServlet {

	private static final long serialVersionUID = 5429400842162897829L;
	private UserService service=new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		String account=request.getParameter("account");
		if(account!=null){
			boolean isOk=service.checkAccount(account);
			if(isOk){
				response.getWriter().println("{\"result\":\"0\",\"tip\":\"账号已存在\"}");
			}else{
				response.getWriter().println("{\"result\":\"1\",\"tip\":\"账号通过\"}");
			}
		}else{
			response.getWriter().println("account is null");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































