package xin.ss.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xin.ss.model.Notice;
import xin.ss.service.NoticeService;
import xin.ss.service.impl.NoticeServiceImpl;
import xin.ss.util.JsonGenerator;


/**
 * 得到公告
 */
@WebServlet("/Web/getNotice")
public class getNotice extends HttpServlet {

	private static final long serialVersionUID = -7394490736224639032L;
	private NoticeService service=new NoticeServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		List<Notice> list=service.queryNotices();
		response.getWriter().println(JsonGenerator.getJson(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































