package controller.chat;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ChatProfile;
import service.face.chat.ChatDeskService;
import service.impl.chat.ChatDeskServiceImpl;

/**
 * Servlet implementation class ChatListController
 */
@WebServlet("/chat/list")
public class ChatListController extends HttpServlet {
	
	private ChatDeskService chatDeskService= new ChatDeskServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8") ;
		resp.setContentType("text/html;charset=UTF-8");
		String param = req.getParameter("chatProfileNo");
		
		//요청파라미터 chatProfileNo를 파싱한다
		int chatProfileNo = 0;
		if( param!=null && !"".equals(param) ) {
			chatProfileNo = Integer.parseInt(param);
		}
		
		//채팅리스트 출력 Ajax 응답
		resp.getWriter().write(chatDeskService.getChat(chatProfileNo));
			
	}
}
