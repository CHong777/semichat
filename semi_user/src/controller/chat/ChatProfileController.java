package controller.chat;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.ChatProfile;
import service.face.chat.ChatProfileService;
import service.impl.chat.ChatProfileServiceImpl;

@WebServlet("/chat/profile")
public class ChatProfileController extends HttpServlet {

	private ChatProfileService chatProfileService = new ChatProfileServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Gson을 이용한 JSON 파싱
		Gson gson = new Gson();
		
		//검색조건 파라미터들을 map으로 받음
		Map<String, String> paramMap = chatProfileService.getParam(req);
		
		//검색조건들을 통한 채팅 프로필 리스트들을 받아옴
		List<ChatProfile> chatProfileList = chatProfileService.getChatProfileList(paramMap);
		
		//json형태 변환
		String str = gson.toJson(chatProfileList);
		
		//채팅 프로필 리스트 - 상담데스크에 전달(Ajax 페이지 경유)
		req.setAttribute("chatProfileList", str);

		//상담데스크로 이동
		req.getRequestDispatcher("/WEB-INF/views/chat/chatProfileAjax.jsp").forward(req, resp);
	}
}
