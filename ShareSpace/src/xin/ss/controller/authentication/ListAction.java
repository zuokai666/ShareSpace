package xin.ss.controller.authentication;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xin.ss.model.Authentication;
import xin.ss.service.AuthenticationService;
import xin.ss.service.impl.AuthenticationServiceImpl;
import xin.ss.util.JsonGenerator;


@WebServlet("/Authentication/ListAction")
public class ListAction extends HttpServlet {

	private static final long serialVersionUID = -1990143151471933639L;
	private AuthenticationService service=new AuthenticationServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		Object id=session.getAttribute("id");
		if(id!=null){
			List<Authentication> list=service.queryIdentity((int)id);
			response.getWriter().println(JsonGenerator.getJson(list));
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































