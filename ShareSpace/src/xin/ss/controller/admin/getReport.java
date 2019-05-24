package xin.ss.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xin.ss.model.ReportDto;
import xin.ss.service.ReportService;
import xin.ss.service.impl.ReportServiceImpl;
import xin.ss.util.JsonGenerator;


/**
 * 得到举报
 */
@WebServlet("/Admin/getReport")
public class getReport extends HttpServlet {

	private static final long serialVersionUID = 3007061262808059927L;
	private ReportService service=new ReportServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		List<ReportDto> list=service.queryReports();
		response.getWriter().println(JsonGenerator.getJson(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































