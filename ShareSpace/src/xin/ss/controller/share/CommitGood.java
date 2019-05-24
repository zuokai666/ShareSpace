package xin.ss.controller.share;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xin.ss.model.Relation;
import xin.ss.service.ShareService;
import xin.ss.service.impl.ShareServiceImpl;
import xin.ss.util.StringMangaer;


/**
 * 提交推荐操作
 */
@WebServlet("/Share/CommitGood")
public class CommitGood extends HttpServlet {
	
	private static final long serialVersionUID = -8695095075028251122L;
	private ShareService service=new ShareServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String result="";
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		String shareid=request.getParameter("shareid");
		Object guest=session.getAttribute("id");
		if(StringMangaer.isNumber(shareid) && guest!=null){
			Relation relation=new Relation();
			relation.setShareid(Integer.parseInt(shareid));
			relation.setGuestid((int)guest);
			boolean isOk=service.commitGood(relation);
			if(isOk){
				result="{\"result\":\"1\",\"tip\":\"推荐成功\"}";
			}else{
				result="{\"result\":\"0\",\"tip\":\"已推荐\"}";
			}
			response.getWriter().println(result);
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































