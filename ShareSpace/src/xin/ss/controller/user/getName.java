package xin.ss.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 得到用户名
 */
@WebServlet("/User/GetName")
public class getName extends HttpServlet {
	
	private static final long serialVersionUID = -1696765732959078338L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		Object name=session.getAttribute("name");
		Object usertype=session.getAttribute("usertype");
		if(name!=null&&usertype!=null){
			int _usertype=(int)usertype;
			if(_usertype==2){
				response.getWriter().println("{\"result\":\"1\",\"tip\":\""+(String)name+"\","
						+ "\"img\":\"<a href='./Admin/Authentication'>管理中心</a>\"}");
			}else {
				response.getWriter().println("{\"result\":\"1\",\"tip\":\""+(String)name+"\"}");
			}
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































