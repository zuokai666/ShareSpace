package xin.ss.controller.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xin.ss.model.Statistics;
import xin.ss.service.UserService;
import xin.ss.service.impl.UserServiceImpl;

/**
 * 得到统计信息
 * @author King
 *
 */
@WebServlet("/Web/GetStatistics")
public class GetStatistics extends HttpServlet {

	private static final long serialVersionUID = 4543957837932434077L;
	private UserService service=new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		ServletContext sc=request.getServletContext();
		Object pv=sc.getAttribute("PageView");
		
		Statistics s=service.GetStatistics();
		if(pv!=null){
			s.setPageview((Integer)pv);
		}else {
			s.setPageview(0);
		}
		response.getWriter().println(s.toJson());
	}
	
}