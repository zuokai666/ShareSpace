package xin.ss.controller.user;


import xin.ss.util.AbstractPageServlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
/**
 * 用户注册页面
 * 
 * @author King
 */
@WebServlet("/User/Register")
public class register extends AbstractPageServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/File/Page/User/Register.html").forward(req, res);
	}
}
