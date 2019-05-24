package xin.ss.controller.share;

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
 * 根据当前页数,与分享内容类型，返回当前页数的分享和评论。
 * <p>需要currentPage,typeid,userid
 * <p>typeid=0,显示全部分类。
 * <p>userid=-1，不需要用户.
 * @author King
 *
 */
@WebServlet("/Share/listAllSharesByPage")
public class listAllSharesByPage extends HttpServlet {
	
	private static final long serialVersionUID = 8898014147024892216L;

	private ShareService service=new ShareServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s=request.getParameter("currentPage");
		String typeid=request.getParameter("typeid");
		if(StringMangaer.isNumber(s)&&StringMangaer.isNumber(typeid)){
			Page page=new Page();
			page.setCurrentPage(Integer.parseInt(s));
			page.setTypeid(Integer.parseInt(typeid));
			page.setUserid(-1);
			service.listAllShares(page);
			response.setContentType(StringMangaer.CONTENTTYPE_JSON);
			response.getWriter().println(page.toJson());
		}else{
			response.getWriter().println("no currentPage or typeid");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}





































