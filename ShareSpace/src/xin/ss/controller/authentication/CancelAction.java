package xin.ss.controller.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xin.ss.model.Authentication;
import xin.ss.service.AuthenticationService;
import xin.ss.service.impl.AuthenticationServiceImpl;
import xin.ss.util.StringMangaer;


@WebServlet("/Authentication/CancelAction")
public class CancelAction extends HttpServlet {

	private static final long serialVersionUID = -6721738895982611273L;
	private AuthenticationService service=new AuthenticationServiceImpl();
	
	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		Object id=session.getAttribute("id");
		String _id=request.getParameter("id");
		if(id!=null&&StringMangaer.isNumber(_id)){
			Authentication auth=new Authentication();
			auth.setUserid((int)id);
			auth.setId(Integer.parseInt(_id));
			auth.setStatus(service.I_CANCEL);
			auth.setResult("用户已取消");
			boolean isOk=service.cancelIdentity(auth);
			if(isOk){
				response.getWriter().println("{\"result\":\"1\",\"tip\":\"取消成功\"}");
			}else {
				response.getWriter().println("{\"result\":\"0\",\"tip\":\"取消失败\"}");
			}
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































