package xin.ss.controller.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xin.ss.model.Report;
import xin.ss.service.ReportService;
import xin.ss.service.impl.ReportServiceImpl;
import xin.ss.util.StringMangaer;
import xin.ss.util.TimeGenerator;

/**
 * 提交举报
 * @author King
 *
 */
@WebServlet("/Web/submitReport")
public class submitReport extends HttpServlet {

	private static final long serialVersionUID = 5630465057543837051L;
	private ReportService service=new ReportServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		HttpSession session=request.getSession();
		String shareid=request.getParameter("shareid");
		String type=request.getParameter("type");
		String content=request.getParameter("content");
		String reString="";
		Object _id=session.getAttribute("id");
		if(shareid!=null&&type!=null&&content!=null&&_id!=null){
			Report r=new Report();
			r.setShareid(Integer.parseInt(shareid));
			r.setContent(StringMangaer.getEscapeString(content));
			r.setUserid((int)_id);
			r.setTime(TimeGenerator.getTime());
			r.setType(type);
			boolean isOk=service.submitReport(r);
			if(isOk){
				reString="{\"result\":\"1\",\"tip\":\"举报成功\"}";
			}else{
				reString="{\"result\":\"0\",\"tip\":\"服务器繁忙，请稍后再试\"}";
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