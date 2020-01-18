package controller.chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ChatProfile;
import service.face.chat.ChatDeskService;
import service.impl.chat.ChatDeskServiceImpl;

/**
 * Servlet implementation class ChatReadController
 */
@WebServlet("/chat/chatRead")
public class ChatReadController extends HttpServlet {

	private ChatDeskService chatDeskService= new ChatDeskServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String param = req.getParameter("chatProfileNo");
		
		int chatProfileNo = 0;
		if( param!=null && !"".equals(param) ) {
			chatProfileNo = Integer.parseInt(param);
		}

		ChatProfile chatProfile = new ChatProfile();
		
		chatProfile.setChatProfileNo(chatProfileNo);
		
		chatDeskService.readChat(chatProfile);
	}
}	
