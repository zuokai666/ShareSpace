package xin.ss.controller.fan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xin.ss.model.Fan;
import xin.ss.service.FanService;
import xin.ss.service.impl.FanServiceImpl;
import xin.ss.util.StringMangaer;


/**
 * 关注或取消关注
 */
@WebServlet("/Fan/FollowAction")
public class FollowAction extends HttpServlet {
	
	private static final long serialVersionUID = -7926694856007286009L;
	private FanService service=new FanServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		Object id=session.getAttribute("id");
		String hostid=request.getParameter("hostid");
		if(id!=null && StringMangaer.isNumber(hostid)){
			Fan fan=new Fan();
			fan.setHostid(Integer.parseInt(hostid));
			fan.setGuestid((int)id);
			boolean isOk=service.follow(fan);
			if(isOk){
				response.getWriter().println("{\"result\":\"1\",\"tip\":\"操作成功\"}");
			}else {
				response.getWriter().println("{\"result\":\"0\",\"tip\":\"操作失败\"}");
			}
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































