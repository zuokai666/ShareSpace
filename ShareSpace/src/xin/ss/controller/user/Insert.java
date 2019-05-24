package xin.ss.controller.user;


import xin.ss.util.AbstractPageServlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
/**
 * 添加分享
 * 
 * 
 * @author King
 */
@WebServlet("/User/Insert")
public class Insert extends AbstractPageServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/File/Page/User/Insert.html").forward(req, res);
	}
}
