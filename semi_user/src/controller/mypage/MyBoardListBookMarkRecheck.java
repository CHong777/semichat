package controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.face.mypage.MyClientListService;
import service.impl.mypage.MyClientListServiceImpl;


@WebServlet("/mypage/recheck")
public class MyBoardListBookMarkRecheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Gson gson = new Gson();
		
		System.out.println( gson.fromJson(req.getParameter("boardno"), String[].class));
		String[] boardno = gson.fromJson(req.getParameter("boardno"), String[].class);
//		for(String s : result) System.out.println("-"+s);
		
		MyClientListService myClientListService = new MyClientListServiceImpl();
		
		List result =  myClientListService.getBookMarkCheck(boardno);
		
		req.setAttribute("result", result);
		
	}

}
