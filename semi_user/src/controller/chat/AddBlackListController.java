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

// 2019-12-01 유진
@WebServlet("/chat/block")
public class AddBlackListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyClientProfileService myClientProfileService= new MyClientProfileServiceImpl();
	
	public AddBlackListController() { }
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { }
	
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		int clientInfoNo = 0;

		// req에서 넘어오는 clientInfoNo 검사
		if (req.getParameter("clientInfoNo") != null && !"".equals(req.getParameter("clientInfoNo"))) {
			clientInfoNo = Integer.parseInt(req.getParameter("clientInfoNo")); // 테스트데이터
		}

		// 해당 client를 차단한 counselor의 ID를 얻어오기 (session 이용)
		MyClientInfo myClientInfo = myClientProfileService.getMyClientProfile(req);
		// req에서 넘어온 clientInfoNo을 set
		myClientInfo.setClientInfoNo(clientInfoNo);
//		System.out.println("차단하기 " + myClientInfo);
		
		// 블랙리스트에 추가 (DB 값 변경)
		myClientInfo=myClientProfileService.addBlackList(myClientInfo);
		
		// json형으로 받아온 정보를 전송
		String json="{\"myclientinfo\" :[\""+myClientInfo.getIsBlock()+"\"]}";
//		System.out.println(json);
		req.setAttribute("json", json);
		
		req.getRequestDispatcher("/WEB-INF/views/chat/clientIsBlock.jsp").forward(req, resp);
	}
}
