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
 * 提交反对操作
 */
@WebServlet("/Share/CommitBad")
public class CommitBad extends HttpServlet {
	
	private static final long serialVersionUID = -8442163105846311960L;
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
			boolean isOk=service.commitBad(relation);
			if(isOk){
				result="{\"result\":\"1\",\"tip\":\"反对成功\"}";
			}else{
				result="{\"result\":\"0\",\"tip\":\"已反对\"}";
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





































