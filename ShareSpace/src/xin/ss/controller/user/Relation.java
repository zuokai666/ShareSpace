package xin.ss.controller.user;


import xin.ss.util.AbstractPageServlet;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
/**
 * 用户关系图页面
 * 
 * @author King
 */
@WebServlet("/User/Relation")
public class Relation extends AbstractPageServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/File/Page/User/Relation.html").forward(req, res);
	}
}
