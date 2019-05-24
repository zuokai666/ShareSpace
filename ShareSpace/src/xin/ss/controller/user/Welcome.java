package xin.ss.controller.user;


import xin.ss.util.AbstractPageServlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
/**
 * 用户个人主页
 * 
 * @author King
 */
@WebServlet("/User/Welcome")
public class Welcome extends AbstractPageServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/File/Page/User/Welcome.html").forward(req, res);
	}
}
