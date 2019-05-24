package xin.ss.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import xin.ss.model.User;
import xin.ss.service.UserService;
import xin.ss.service.impl.UserServiceImpl;
import xin.ss.util.StringMangaer;


/**
 * 自动登录
 */
@WebServlet("/User/AutoLoginAction")
public class AutoLoginAction extends HttpServlet {
	
	private static final long serialVersionUID = -4582144195288984184L;
	private UserService service=new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		//首先判断是否已经登录
		HttpSession session=request.getSession();
		Object idObject=session.getAttribute("id");
		if(idObject!=null){
			response.getWriter().println(StringMangaer.RESULT_LOGIN_SUCCESS);
			return;
		}
		//然后使用传入的值进行登录验证
		User user=new User();
		String result="";
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user1 = service.login(user);
		if(user1!=null){
			session.setAttribute("usertype",user1.getUsertype());
			session.setAttribute("id",user1.getId());
			session.setAttribute("name",user1.getName());
			result=StringMangaer.RESULT_LOGIN_SUCCESS;
		}else{
			result=StringMangaer.RESULT_LOGIN_FAIL;
		}
		response.getWriter().println(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































