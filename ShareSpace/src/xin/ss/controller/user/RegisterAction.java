package xin.ss.controller.user;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xin.ss.model.User;
import xin.ss.service.UserService;
import xin.ss.service.impl.UserServiceImpl;
import xin.ss.util.StringMangaer;


/**
 * 注册
 * @author King
 */
@WebServlet("/User/RegisterAction")
public class RegisterAction extends HttpServlet {
	
	private static final long serialVersionUID = -8078485748126031837L;
	private UserService service=new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		User user=new User();
		String result="";
		String account=request.getParameter("account");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		if((!account.matches("^[A-Za-z0-9]*$"))||StringMangaer.pwdIsWrong(password)||name.length()>15){
			response.getWriter().println("{\"result\":\"0\",\"tip\":\"注册失败\"}");
    		return;
    	}
		user.setAccount(account);
		user.setName(StringMangaer.getEscapeString(name));
		user.setPassword(password);
		user.setRegistertime(getTime());
		boolean isOk = service.register(user);
		if(isOk){
			result="{\"result\":\"1\",\"tip\":\"注册成功\"}";
		}else{
			result="{\"result\":\"0\",\"tip\":\"注册失败\"}";
		}
		response.getWriter().println(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private String getTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}





































