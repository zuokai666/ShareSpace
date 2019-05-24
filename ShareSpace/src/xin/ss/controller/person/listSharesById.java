package xin.ss.controller.person;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xin.ss.model.Page;
import xin.ss.service.ShareService;
import xin.ss.service.impl.ShareServiceImpl;
import xin.ss.util.*;

/**
 * 返回用户的个人首页的分享和评论。
 * <p>需要currentPage,typeid
 * <p>typeid=0,显示全部分类。
 * <p>access_id:返回该用户个人首页
 * @author King
 *
 */
@WebServlet("/Share/listSharesById")
public class listSharesById extends HttpServlet {
	
	private static final long serialVersionUID = 7482028555309969891L;
	private ShareService service=new ShareServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s=request.getParameter("currentPage");
		String typeid=request.getParameter("typeid");
		Object access_id=request.getSession().getAttribute("access_id");
		if(StringMangaer.isNumber(s)&&StringMangaer.isNumber(typeid)&&access_id!=null){
			Page page=new Page();
			page.setCurrentPage(Integer.parseInt(s));
			page.setTypeid(Integer.parseInt(typeid));
			page.setUserid((int)access_id);
			service.listAllShares(page);
			response.setContentType(StringMangaer.CONTENTTYPE_JSON);
			response.getWriter().println(page.toJson());
		}else{
			response.getWriter().println("no currentPage or typeid userid");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}





































