package xin.ss.controller.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xin.ss.model.Message;
import xin.ss.service.MessageService;
import xin.ss.service.impl.MessageServiceImpl;
import xin.ss.util.StringMangaer;


/**
 * 设置收件箱已读
 */
@WebServlet("/Message/updateInBoxMessage")
public class updateInBoxMessage extends HttpServlet {

	private static final long serialVersionUID = 1090394781741089350L;
	private MessageService service=new MessageServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		Object id=session.getAttribute("id");
		String ids=request.getParameter("ids");
		if(id!=null&&ids!=null){
			String[] arr=ids.split(",");
			List<Message> l=new ArrayList<>();
			for(int i=0;i<arr.length;i++){
				Message m=new Message();
				m.setId(Integer.parseInt(arr[i]));
				m.setHostid((int)id);
				l.add(m);
			}
			service.setRead(l);
			response.getWriter().println(StringMangaer.RESULT_UPDATE_SUCCESS);
		}else{
			response.getWriter().println(xin.ss.util.StringMangaer.RESULT_NOT_LOGIN);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}





































