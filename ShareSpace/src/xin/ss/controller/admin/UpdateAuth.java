package xin.ss.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xin.ss.model.Authentication;
import xin.ss.service.AuthenticationService;
import xin.ss.service.impl.AuthenticationServiceImpl;
import xin.ss.util.StringMangaer;


@WebServlet("/Admin/UpdateAuth")
public class UpdateAuth extends HttpServlet {

	private static final long serialVersionUID = 3697039960187962851L;
	private AuthenticationService service=new AuthenticationServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		String status=request.getParameter("status");
		String id=request.getParameter("id");
		String result=request.getParameter("result");
		String reString="";
		if(id!=null&&status!=null&&result!=null){
			Authentication auth=new Authentication();
			auth.setId(Integer.parseInt(id));
			auth.setStatus(Integer.parseInt(status));
			auth.setResult(StringMangaer.getEscapeString(result));
			boolean isOk=service.updateIdentity(auth);		
			if(isOk){
				reString=xin.ss.util.StringMangaer.RESULT_UPDATE_SUCCESS;
			}else{
				reString=xin.ss.util.StringMangaer.RESULT_UPDATE_FAIL;
			}
			response.getWriter().println(reString);
		}else {
			response.getWriter().println(StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































