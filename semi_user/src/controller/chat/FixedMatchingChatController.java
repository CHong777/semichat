package controller.chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MyClientInfo;
import service.face.chat.MyClientProfileService;
import service.impl.chat.MyClientProfileServiceImpl;

@WebServlet("/chat/fixedMatch")
public class FixedMatchingChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyClientProfileService myClientProfileService= new MyClientProfileServiceImpl();

	public FixedMatchingChatController() {
		
	}
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		int clientInfoNo =0;
		
		// req에서 넘어오는 clientInfoNo 검사
		if (req.getParameter("clientInfoNo") != null && !"".equals(req.getParameter("clientInfoNo"))) {
			clientInfoNo = Integer.parseInt(req.getParameter("clientInfoNo")); // 테스트데이터
		} 
		
		// counselorId, clientNick을 가져온다
		MyClientInfo myClientInfo = myClientProfileService.getMyClientProfile(req);
		myClientInfo.setClientInfoNo(clientInfoNo);
//		System.out.println("고정매칭 "+myClientInfo);

		// 고정 매칭 상태 update하러 가기
		myClientInfo=myClientProfileService.fixedMatchingChat(myClientInfo);
//		System.out.println("고정매칭2 "+myClientInfo);
		
		String json="{\"myclientinfo\" :[\"" + myClientInfo.getIsFixedMatch()+"\"]}";
//		System.out.println(json);
		req.setAttribute("json", json);

		req.getRequestDispatcher("/WEB-INF/views/chat/clientIsFixed.jsp").forward(req, resp);
	}
}
