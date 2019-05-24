package xin.ss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/index.html")
public class PageViewFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ServletContext sc=request.getServletContext();
		Object pv=sc.getAttribute("PageView");
		if(pv!=null){
			sc.setAttribute("PageView", (Integer)pv+1);
		}else{
			sc.setAttribute("PageView",1);
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}
}



























