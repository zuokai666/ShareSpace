package xin.ss.controller.authentication;
import xin.ss.util.AbstractPageServlet;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

/**
 * 认证入口
 * 
 * @author King
 */
@WebServlet("/Authentication/Index")
public class Index extends AbstractPageServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/File/Page/Authentication/Index.html").forward(req, res);
	}
}
