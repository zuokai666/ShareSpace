package xin.ss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/Admin/*")
public class AdminFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest r=(HttpServletRequest)request;
		HttpSession s=r.getSession();
		Object _usertype=s.getAttribute("usertype");
		if(_usertype!=null){
			if(2==(int)_usertype){
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
		
	}
}



























