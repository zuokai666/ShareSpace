package xin.ss.controller.message;


import xin.ss.util.AbstractPageServlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
/**
 * 收件箱
 * 
 * @author King
 */
@WebServlet("/Message/InBox")
public class InBox extends AbstractPageServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/File/Page/Message/InBox.html").forward(req, res);
	}
}
