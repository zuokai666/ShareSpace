package xin.ss.controller.web;


import xin.ss.util.AbstractPageServlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
/**
 * 网站公告
 * 
 * @author King
 */
@WebServlet("/Web/Notice")
public class Notice extends AbstractPageServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/File/Page/Web/Notice.html").forward(req, res);
	}
}
