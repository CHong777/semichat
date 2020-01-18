package controller.chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ClientInfo;
import service.face.chat.MyClientProfileService;
import service.impl.chat.MyClientProfileServiceImpl;

@WebServlet("/chat/register/myclientinfo")
public class RegisterCustomerInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyClientProfileService myClientProfileService= new MyClientProfileServiceImpl();
	
	public RegisterCustomerInfoController() { }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		int clientInfoNo =0;
		
		// req에서 넘어오는 clientInfoNo 검사
		if (req.getParameter("clientInfoNo") != null && !"".equals(req.getParameter("clientInfoNo"))) {
			clientInfoNo = Integer.parseInt(req.getParameter("clientInfoNo")); // 테스트데이터
		} 
		
		// clientInfoNo을 이용해 고객의 정보 select 후 clientInfoNo 셋팅
		ClientInfo clientInfo = myClientProfileService.selectClientProfile(clientInfoNo);
		clientInfo.setClientInfoNo(clientInfoNo);

		// chatProfile의 ajax에 대한 리턴
		String json="{\"clientinfo\" :[\"" + clientInfo.getClientInfoNo()+"\""
						+ ",\""+clientInfo.getClientName()+"\""
						+ ",\""+clientInfo.getClientPhoneNum()+"\""
						+ ",\""+clientInfo.getLastChatDate()+"\""
						+ ",\""+clientInfo.getCounselorName()+"\""
						+ ",\""+clientInfo.getChatMemo()+"\"]}";
//		System.out.println("json"+json);
		
		req.setAttribute("json", json);
		req.getRequestDispatcher("/WEB-INF/views/chat/clientinfo.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		int clientInfoNo =0;
		
		// req에서 넘어오는 clientInfoNo 검사
		if (req.getParameter("clientInfoNo") != null && !"".equals(req.getParameter("clientInfoNo"))) {
			clientInfoNo = Integer.parseInt(req.getParameter("clientInfoNo")); // 테스트데이터
		} 

//		고객정보 form에 입력한 정보 받아옴
		ClientInfo clientInfo = myClientProfileService.getClientProfile(req);
		clientInfo.setClientInfoNo(clientInfoNo);
//		System.out.println("client "+clientInfo);

//		고객 정보 DB 등록
		myClientProfileService.registerCustomerInfo(clientInfo);
		
		String json="{\"clientinfo\" :[\"" + clientInfo.getClientInfoNo()+"\""
				+ ",\""+clientInfo.getClientName()+"\""
				+ ",\""+clientInfo.getClientPhoneNum()+"\""
				+ ",\""+clientInfo.getLastChatDate()+"\""
				+ ",\""+clientInfo.getCounselorName()+"\""
				+ ",\""+clientInfo.getChatMemo()+"\"]}";
//		System.out.println("json"+json);
		
		req.setAttribute("json", json);
		req.getRequestDispatcher("/WEB-INF/views/chat/registerInfo.jsp").forward(req, resp);		
	}
	
}
