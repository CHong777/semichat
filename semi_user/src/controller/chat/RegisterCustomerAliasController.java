package controller.chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ClientInfo;
import dto.MyClientInfo;
import service.face.chat.MyClientProfileService;
import service.impl.chat.MyClientProfileServiceImpl;

@WebServlet("/chat/myclientalias")
public class RegisterCustomerAliasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyClientProfileService myClientProfileService= new MyClientProfileServiceImpl();

	public RegisterCustomerAliasController(){ }
	
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
		MyClientInfo myClientInfo = myClientProfileService.selectMyClientProfile(clientInfoNo);
		myClientInfo.setClientInfoNo(clientInfoNo);
//		System.out.println("mycliet"+myClientInfo);
		
		// chatProfile의 ajax에 대한 리턴
		String json="{\"myclientinfo\" :[\"" + myClientInfo.getMyClientInfoNo()+"\""
						+ ",\""+myClientInfo.getCounselorNo()+"\""
						+ ",\""+myClientInfo.getCounselorId()+"\""
						+ ",\""+myClientInfo.getIsBlock()+"\""
						+ ",\""+myClientInfo.getIsFixedMatch()+"\""
						+ ",\""+myClientInfo.getClientNick()+"\""
						+ ",\""+myClientInfo.getClientInfoNo()+"\"]}";
//		System.out.println(json);
		
		req.setAttribute("json", json);
		req.getRequestDispatcher("/WEB-INF/views/chat/myClientinfo.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		int clientInfoNo =0;
		
		// req에서 넘어오는 clientInfoNo 검사
		if (req.getParameter("clientInfoNo") != null && !"".equals(req.getParameter("clientInfoNo"))) {
			clientInfoNo = Integer.parseInt(req.getParameter("clientInfoNo"));
		} 
//		String data=req.getParameter("clientNick");
//		System.out.println(data);
		
//		form에 입력한 별칭, counselorId 받아옴
		MyClientInfo myClientInfo = myClientProfileService.getMyClientProfile(req);
		myClientInfo.setClientInfoNo(clientInfoNo);
		System.out.println("aaa"+myClientInfo);

//		DB에 넣기
		myClientProfileService.getMyClientProfile(myClientInfo);
		String json="{\"myclientinfo\" :[\"" + myClientInfo.getClientNick()+"\"]}";
		req.setAttribute("json", json);
//		System.out.println(json);
		req.getRequestDispatcher("/WEB-INF/views/chat/myClientNick.jsp").forward(req, resp);

	}
}
