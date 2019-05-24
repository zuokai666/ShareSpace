package xin.ss.controller.person;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xin.ss.util.StringMangaer;



@WebServlet("/User/Index")
public class Index extends HttpServlet {

	private static final long serialVersionUID = 1886578944532986130L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String accessid=request.getParameter("id");
		Object idObj=session.getAttribute("id");
		if(accessid==null&&idObj==null){
			response.getWriter().println("no userid");
			return;
		}else if(accessid==null){
			session.setAttribute("access_id",(int)idObj);
		}else {
			if(StringMangaer.isNumber(accessid)){
				session.setAttribute("access_id",Integer.parseInt(accessid));
			}else {
				response.getWriter().println("no userid");
				return;
			}
		}
		request.getRequestDispatcher("/File/Page/User/Index.html").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}