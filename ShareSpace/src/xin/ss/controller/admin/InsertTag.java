package xin.ss.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xin.ss.service.TagService;
import xin.ss.service.impl.TagServiceImpl;
import xin.ss.util.StringMangaer;


@WebServlet("/Admin/InsertTag")
public class InsertTag extends HttpServlet {

	private static final long serialVersionUID = 1962114571489947982L;
	private TagService service=new TagServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		String name=request.getParameter("name");
		String reString="";
		if(name!=null){
			xin.ss.model.Tag t=new xin.ss.model.Tag();
			t.setName(StringMangaer.getEscapeString(name));
			boolean isOk=service.insertTag(t);
			if(isOk){
				reString=xin.ss.util.StringMangaer.RESULT_INSERT_SUCCESS;
			}else{
				reString=xin.ss.util.StringMangaer.RESULT_INSERT_FAIL;
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





































