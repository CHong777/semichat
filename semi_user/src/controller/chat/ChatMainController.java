package controller.chat;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ChatProfile;
import dto.FrequentReply;
import service.face.chat.ChatProfileService;
import service.face.chat.MyClientProfileService;
import service.impl.chat.ChatProfileServiceImpl;
import service.impl.chat.MyClientProfileServiceImpl;
import util.Paging;

@WebServlet("/chat/main")
public class ChatMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ChatProfileService chatProfileService = new ChatProfileServiceImpl();
	// 2019-11-30 유진 : 자주 쓰는 답변 나타내주기
	private MyClientProfileService myClientProfileService= new MyClientProfileServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<ChatProfile> chatProfileList = chatProfileService.getChatProfileList();
		List<FrequentReply> replyList = myClientProfileService.getReply();
		//테스트
		//System.out.println(chatProfileList);
		
		//채팅 프로필 리스트 -상담데스크에 전달
		req.setAttribute("chatProfileList", chatProfileList);
		//2019-11-30 유진 :디비에 등록되어 있는 자주 쓰는 답변들 데스크로 전달
		req.setAttribute("replyList", replyList);
		
		//상담데스크로 이동
		req.getRequestDispatcher("/WEB-INF/views/chat/chatMain.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//페이징 - new, 진행중일때는 X
		//Paging paging = chatProfileService.getPaging(req);

		//채팅 프로필 목록 조회
		//List<ChatProfile> chatProfileList = chatProfileService.getChatProfileList(paging);
		
		//채팅 프로필 목록 -뷰로 전달
		//req.setAttribute("list", chatProfileList);
	
	
	}
}
