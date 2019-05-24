package xin.ss.controller.message;


import xin.ss.util.AbstractPageServlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
/**
 * 发件
 * 
 * @author King
 */
@WebServlet("/Message/Send")
public class Send extends AbstractPageServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/File/Page/Message/Send.html").forward(req, res);
	}
}
