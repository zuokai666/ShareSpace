package xin.ss.controller.user;


import xin.ss.util.AbstractPageServlet;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
/**
 * 用户设置页面
 * 
 * @author King
 */
@WebServlet("/User/Set")
public class Set extends AbstractPageServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/File/Page/User/Set.html").forward(req, res);
	}
}
