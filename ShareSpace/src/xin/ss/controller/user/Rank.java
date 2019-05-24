package xin.ss.controller.user;


import xin.ss.util.AbstractPageServlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
/**
 * Rank排行
 * 
 * @author King
 */
@WebServlet("/User/Rank")
public class Rank extends AbstractPageServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/File/Page/User/Rank.html").forward(req, res);
	}
}
