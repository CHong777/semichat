package controller.chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Chat;
import dto.ChatProfile;
import service.face.chat.ChatDeskService;
import service.impl.chat.ChatDeskServiceImpl;

/**
 * Servlet implementation class ChatSubmitController
 */
@WebServlet("/chat/chatsubmit")
public class ChatSubmitController extends HttpServlet {

	private ChatDeskService chatDeskService= new ChatDeskServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String param = req.getParameter("chatProfileNo");
		//요청파라미터 chatProfileNo를 파싱한다
		int chatProfileNo = 0;
		if( param!=null && !"".equals(param) ) {
			chatProfileNo = Integer.parseInt(param);
		}

		String chatContent = req.getParameter("chatContent");
		
		//값 세팅
		ChatProfile chatProfile = new ChatProfile();
		chatProfile.setChatProfileNo(chatProfileNo);
		
		//챗 프로파일 번호를 통한  정보 추출
		chatDeskService.getClientIdByChatProfileNo(chatProfile);
		
		System.out.println("챗 프로필 메신저 번호 : " + chatProfile);
		
		Chat chat = new Chat();
		chat = chatDeskService.settingChat(chatProfile, chatContent, req);
		
		System.out.println("챗 테스트 : " + chat);
		
		//chatProfile 테이블의 LastChatDate를 전송한 시간으로 업데이트
		chatDeskService.setLastChatDate(chatProfile);
		
		//insert 성공 여부 값 저장
		int result = chatDeskService.multipleSubmit(chat, chatProfile);

		req.setAttribute("result", result);

		req.getRequestDispatcher("/WEB-INF/views/chat/chatSubmit.jsp").forward(req, resp);
		
	}
}
