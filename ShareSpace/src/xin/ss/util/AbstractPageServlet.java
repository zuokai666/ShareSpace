package xin.ss.util;

import java.io.IOException;

import javax.servlet.*;
/**
 * 所有页面的抽象父类
 * @author zk
 *
 */
public abstract class AbstractPageServlet implements Servlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public abstract void service(ServletRequest req, ServletResponse res) throws ServletException, IOException;

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void destroy() {
	}

}
