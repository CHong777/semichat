package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MyClientInfo;
import service.face.mypage.MyClientListService;
import service.impl.mypage.MyClientListServiceImpl;


@WebServlet("/mypage/clientdelete")
public class MyClientListDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyClientListDeleteController() {}
	
	private MyClientListService myClientListService = new MyClientListServiceImpl(); 

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MyClientInfo deleteBoard = new MyClientInfo(); 

		String[] arr = req.getParameterValues("checkRow");


		for(int i=0; i<arr.length; i++) {

			deleteBoard.setClientInfoNo(Integer.parseInt(arr[i]));
			myClientListService.delete(deleteBoard);

		}
		resp.sendRedirect("/mypage/myclient");


	}
}
