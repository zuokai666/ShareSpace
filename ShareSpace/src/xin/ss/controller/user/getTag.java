package xin.ss.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import xin.ss.model.Tag;
import xin.ss.service.TagService;
import xin.ss.service.impl.TagServiceImpl;
import xin.ss.util.JsonGenerator;


/**
 * 得到标签列表
 */
@WebServlet("/User/getTag")
public class getTag extends HttpServlet {

	private static final long serialVersionUID = 7009510164322549929L;
	private TagService service=new TagServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		List<Tag> list=service.queryTags();
		response.getWriter().println(JsonGenerator.getJson(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































