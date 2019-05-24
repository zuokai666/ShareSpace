package xin.ss.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xin.ss.model.UserTag;
import xin.ss.service.UserService;
import xin.ss.service.impl.UserServiceImpl;


/**
 * 更改标签操作
 */
@WebServlet("/User/updateUserTags")
public class updateUserTags extends HttpServlet {

	private static final long serialVersionUID = -607060896221133378L;
	private UserService service=new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		Object id=session.getAttribute("id");
		String ids=request.getParameter("ids");
		if(id!=null&&ids!=null){
			boolean isOk=false;
			if(ids.equals("")){
				//用户删除所有标签
				isOk=service.delUserTag((int)id);
			}else{
				String[] arr=ids.split(",");
				List<UserTag> l=new ArrayList<>();
				for(int i=0;i<arr.length;i++){
					UserTag m=new UserTag();
					m.setTagid(Integer.parseInt(arr[i]));
					m.setUserid((int)id);
					l.add(m);
				}
				isOk=service.updateUserTag(l);
			}
			if(isOk){
				response.getWriter().println(xin.ss.util.StringMangaer.RESULT_UPDATE_SUCCESS);
			}else {
				response.getWriter().println(xin.ss.util.StringMangaer.RESULT_UPDATE_FAIL);
			}
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































