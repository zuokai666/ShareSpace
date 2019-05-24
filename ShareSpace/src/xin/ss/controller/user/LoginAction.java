package xin.ss.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import xin.ss.model.User;
import xin.ss.service.UserService;
import xin.ss.service.impl.UserServiceImpl;


/**
 * 检查登录是否成功
 */
@WebServlet("/User/LoginAction")
public class LoginAction extends HttpServlet {
	
	private static final long serialVersionUID = 7201190472562669932L;
	private UserService service=new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new User();
		String result="";
		HttpSession session=request.getSession();
		String remember=request.getParameter("remember");//若选择，为 "on"，否则为"undefined"
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user1 = service.login(user);
		if(user1!=null){
			if(remember.equals("on")){
				//加判断是否已存在当前用户的Cookie，避免重复Set-Cookie
				if(!isExistCookies(request.getCookies(),user1)){
					setCookies(user1,60*60*24*7,request,response);
				}
			}else{
				setCookies(user1,0,request,response);
			}
			session.setAttribute("usertype",user1.getUsertype());
			session.setAttribute("id",user1.getId());
			session.setAttribute("name",user1.getName());
			result="{\"result\":\"1\",\"tip\":\"登录成功\",\"name\":\""+user1.getName()+"\"}";
		}else{
			result=xin.ss.util.StringMangaer.RESULT_LOGIN_FAIL;
		}
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		response.getWriter().println(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void setCookies(User user1,int time,HttpServletRequest request, HttpServletResponse response){
		 Cookie cookie1 = new Cookie("account",user1.getAccount());
		 Cookie cookie2 = new Cookie("password",user1.getPassword());
		 Cookie cookie3 = new Cookie("remember","on");
		 cookie1.setMaxAge(time);
		 cookie2.setMaxAge(time);
		 cookie3.setMaxAge(time);
		 cookie1.setPath(request.getContextPath());
		 cookie2.setPath(request.getContextPath());
		 cookie3.setPath(request.getContextPath());
		 response.addCookie(cookie1);
		 response.addCookie(cookie2);
		 response.addCookie(cookie3);
	}
	private boolean isExistCookies(Cookie[] cookies,User user) {
		int result=0;
		for(int i=0;i<cookies.length;i++){
			Cookie cookie=cookies[i];
			if(cookie.getName().equals("account")){
				if(cookie.getValue().equals(user.getAccount())){
					result++;
				}
			}else if(cookie.getName().equals("password")){
				if(cookie.getValue().equals(user.getPassword())){
					result++;
				}
			}else if(cookie.getName().equals("remember")){
				if(cookie.getValue().equals("on")){
					result++;
				}
			}
		}
		if(result==3){
			return true;
		}
		return false;
	}
}





































