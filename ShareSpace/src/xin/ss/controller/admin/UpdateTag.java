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


@WebServlet("/Admin/UpdateTag")
public class UpdateTag extends HttpServlet {

	private static final long serialVersionUID = -9125203488055765286L;
	private TagService service=new TagServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		String name=request.getParameter("name");
		String id=request.getParameter("id");
		String reString="";
		if(name!=null&&id!=null){
			xin.ss.model.Tag t=new xin.ss.model.Tag();
			t.setName(StringMangaer.getEscapeString(name));
			t.setId(Integer.parseInt(id));
			boolean isOk=service.updateTag(t);
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





































