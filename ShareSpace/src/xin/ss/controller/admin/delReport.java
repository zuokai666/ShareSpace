package xin.ss.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xin.ss.service.ReportService;
import xin.ss.service.impl.ReportServiceImpl;


/**
 * 删除举报
 */
@WebServlet("/Admin/delReport")
public class delReport extends HttpServlet {

	private static final long serialVersionUID = 9169882692662176296L;
	private ReportService service=new ReportServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		String id=request.getParameter("id");
		if(id!=null){
			boolean isOk=service.deleteResport(Integer.parseInt(id));
			String result="";
			if(isOk){
				result=xin.ss.util.StringMangaer.RESULT_DELETE_SUCCESS;
			}else{
				result=xin.ss.util.StringMangaer.RESULT_DELETE_FAIL;
			}
			response.getWriter().println(result);
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































