package controller.chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.ChatProfile;
import dto.ClientInfo;
import dto.MyClientInfo;
import service.face.chat.ChatProfileService;
import service.face.chat.MyClientProfileService;
import service.impl.chat.ChatProfileServiceImpl;
import service.impl.chat.MyClientProfileServiceImpl;

@WebServlet("/chat/profile/detail")
public class ChatProfileDetailController extends HttpServlet {

	ChatProfileService chatProfileService = new ChatProfileServiceImpl();
	MyClientProfileService myClientProfileService = new MyClientProfileServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Gson을 이용한 JSON 파싱
		Gson gson = new Gson();

		//채팅 프로필 넘버를 가져와 ChatProfile DTO에 저장
		ChatProfile chatProfile = chatProfileService.getChatProfileNo(req);
		
		//------상담자 번호를 가져온다(유진 스타일 코드로 변형)----------
		String param = req.getParameter("clientInfoNo");
		
		int clientInfoNo = -1;
		if(param != null && !"".equals(param)) {
			clientInfoNo = Integer.parseInt(param);
		}
		
		//상담자 및 나만의 상담자 정보를 가져온다 //객체 생성
		ClientInfo clientInfo = myClientProfileService.selectClientProfile(clientInfoNo);
		MyClientInfo myClientInfo = myClientProfileService.selectMyClientProfile(clientInfoNo);

		//--------------------------------------------------
		
		//현재 로그인된 닉네임을 가져온다
		String counselornickname = (String) req.getSession().getAttribute("counselornickname");
		if(counselornickname != null && !("").equals(counselornickname))
			chatProfile.setCounselorID(counselornickname);
		
		//해당 채팅 프로필 상태를 1로 업데이트한다(new -> 진행중) + nickname츄가
		chatProfileService.updateStatus(chatProfile);
		
		//진행중인 상태의 채팅 프로필 목록을 가져온다
		//검색조건 파라미터들을 map으로 받음
		Map<String, String> paramMap = chatProfileService.getParam(req);
				
		//검색조건들을 통한 채팅 프로필 리스트들을 받아옴
		List<ChatProfile> chatProfileList = chatProfileService.getChatProfileList(paramMap);
		
		//받아온 정보
		Map<String, Object> json_map = new HashMap<String, Object>();
		
		//DTO들 전달
		json_map.put("chatProfileList", chatProfileList);
		json_map.put("clientInfo", clientInfo);
		json_map.put("myClientInfo", myClientInfo);
	
		//JSON 형태 변환
		String json = gson.toJson(json_map);
		
		req.setAttribute("chatProfileDetail", json);

		//상담데스크로 이동
		req.getRequestDispatcher("/WEB-INF/views/ajax/chat/chatProfileDetailAjax.jsp").forward(req, resp);
	}
}
