package xin.ss.controller.user;


import xin.ss.util.AbstractPageServlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
/**
 * 用户登录页面
 * 
 * @author King
 */
@WebServlet("/User/Login")
public class Login extends AbstractPageServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/File/Page/User/Login.html").forward(req, res);
	}
}
