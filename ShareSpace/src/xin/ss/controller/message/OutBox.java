package xin.ss.controller.message;


import xin.ss.util.AbstractPageServlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
/**
 * 发件箱
 * 
 * @author King
 */
@WebServlet("/Message/OutBox")
public class OutBox extends AbstractPageServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/File/Page/Message/OutBox.html").forward(req, res);
	}
}
