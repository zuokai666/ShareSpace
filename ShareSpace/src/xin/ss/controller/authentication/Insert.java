package xin.ss.controller.authentication;
import xin.ss.util.AbstractPageServlet;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

/**
 * 添加认证
 * 
 * @author King
 */
@WebServlet("/Authentication/Insert")
public class Insert extends AbstractPageServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/File/Page/Authentication/Insert.html").forward(req, res);
	}
}
