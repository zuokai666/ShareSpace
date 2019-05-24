package xin.ss.controller.fan;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xin.ss.model.FanDto;
import xin.ss.service.FanService;
import xin.ss.service.impl.FanServiceImpl;
import xin.ss.util.JsonGenerator;


/**
 * 关注列表
 */
@WebServlet("/Fan/FollowIndex")
public class FollowIndex extends HttpServlet {
	
	private static final long serialVersionUID = -8197943626991813995L;
	private FanService service=new FanServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		Object id=session.getAttribute("id");
		if(id!=null){
			List<FanDto> isOk=service.queryFollow((int)id);
			response.getWriter().println(JsonGenerator.getJson(isOk));
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































