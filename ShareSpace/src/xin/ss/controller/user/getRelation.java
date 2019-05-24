package xin.ss.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xin.ss.model.RelationDto;
import xin.ss.service.RelationService;
import xin.ss.service.impl.RelationServiceImpl;
import xin.ss.util.JsonGenerator;


/**
 * 得到用户关系图数据
 */
@WebServlet("/User/getRelation")
public class getRelation extends HttpServlet {

	private static final long serialVersionUID = -6797550557948671037L;
	private RelationService service=new RelationServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		Object id=session.getAttribute("id");
		if(id!=null){
			List<RelationDto> list=service.queryScore((int)id);
			response.getWriter().println(JsonGenerator.getJson(list));
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































