package xin.ss.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xin.ss.model.Authentication;
import xin.ss.service.AuthenticationService;
import xin.ss.service.impl.AuthenticationServiceImpl;
import xin.ss.util.JsonGenerator;


@WebServlet("/Admin/ListAuths")
public class ListAuths extends HttpServlet {

	private static final long serialVersionUID = 8834750131184353130L;
	private AuthenticationService service=new AuthenticationServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		String status=request.getParameter("status");
		int sid=0;
		if(status!=null){
			sid=Integer.parseInt(status);
		}
		List<Authentication> list=service.queryAuthenticationsByStatus(sid);
		response.getWriter().println(JsonGenerator.getJson(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































