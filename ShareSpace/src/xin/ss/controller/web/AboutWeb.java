package xin.ss.controller.web;


import xin.ss.util.AbstractPageServlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
/**
 * 关于网站
 * 
 * @author King
 */
@WebServlet("/Web/AboutWeb")
public class AboutWeb extends AbstractPageServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/File/Page/Web/AboutWeb.html").forward(req, res);
	}
}
