package xin.ss.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xin.ss.model.UserDto;
import xin.ss.service.UserService;
import xin.ss.service.impl.UserServiceImpl;
import xin.ss.util.JsonGenerator;
import xin.ss.util.StringMangaer;


@WebServlet("/User/GetRanks")
public class GetRanks extends HttpServlet {

	private static final long serialVersionUID = 6780815438213956361L;
	private UserService service=new UserServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num=request.getParameter("num");
		response.setContentType(xin.ss.util.StringMangaer.CONTENTTYPE_JSON);
		List<UserDto> s=service.listUserRanks();
		if(StringMangaer.isNumber(num)){
			int n=Integer.parseInt(num);
			List<UserDto> copy=new ArrayList<>();
			if(n>s.size()){
				n=s.size();
			}
			for(int i=0;i<n;i++){
				copy.add(s.get(i));
			}
			response.getWriter().println(JsonGenerator.getJson(copy));
		}else{
			response.getWriter().println(JsonGenerator.getJson(s));
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}