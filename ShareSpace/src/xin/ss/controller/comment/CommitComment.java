package xin.ss.controller.comment;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import xin.ss.model.Comment;
import xin.ss.service.CommentService;
import xin.ss.service.impl.CommentServiceImpl;
import xin.ss.util.StringMangaer;


/**
 * 提交评论操作
 */
@WebServlet("/Comment/CommitComment")
public class CommitComment extends HttpServlet {
	
	private static final long serialVersionUID = -8879312301205227459L;
	private CommentService service=new CommentServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(StringMangaer.CONTENTTYPE_JSON);
		HttpSession session=request.getSession();
		String result="";
		String content=request.getParameter("content");
		String shareid=request.getParameter("shareid");
		Object _id=session.getAttribute("id");
		if(content!=null&&StringMangaer.isNumber(shareid)&&_id!=null){
			if(StringMangaer.isNull(content)){
				result=StringMangaer.RESULT_INSERT_FAIL;
				response.getWriter().println(result);
				return;
			}
			Comment comment=new Comment();
			comment.setContent(StringMangaer.getEscapeString(content));
			comment.setUserid((int)_id);
			comment.setShareid(Integer.parseInt(shareid));
			comment.setCommenttime(getTime());
			boolean isOk=service.commitComment(comment);
			if(isOk){
				result=StringMangaer.RESULT_INSERT_SUCCESS;
			}else{
				result=StringMangaer.RESULT_INSERT_FAIL;
			}
			response.getWriter().println(result);
		}else{
			response.getWriter().println(StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private String getTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}





































