package service.impl.chat;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.face.chat.ChatDeskDao;
import dao.impl.chat.ChatDeskDaoImpl;
import dto.Chat;
import dto.ChatProfile;
import service.face.chat.ChatDeskService;

public class ChatDeskServiceImpl implements ChatDeskService {

	 private ChatDeskDao chatDeskDao = new ChatDeskDaoImpl();
	
	@Override
	public ChatProfile getClientIdByChatProfileNo(ChatProfile chatProfile) {

		return chatDeskDao.selectClientIdByChatProfileNo(chatProfile);
	}

	@Override
	public Chat settingChat(ChatProfile chatProfile, String chatContent, HttpServletRequest req) {
		
		Chat chat = new Chat();
		
		HttpSession session = null;
		session = req.getSession();
		
		//채팅 프로필번호, FromID, TOID, Content 설정
		chat.setChatProfileNo(chatProfile.getChatProfileNo());
		chat.setFromID((String) session.getAttribute("counselorid"));
		chat.setToID(chatProfile.getClientID());
		chat.setChatContent(chatContent);
		
		return chat;
	}

	@Override
	public int multipleSubmit(Chat chat, ChatProfile chatProfile) {

		//초기 값
		int result = 0;
		//세미챗에 삽입
		result = chatDeskDao.insertSemiChat(chat);
		// result 가 -1이면 삽입 실패, 리턴
		if(result  == -1)
			return result;
		
		//클라이언트 DB에 삽입
		//1이면 카카오톡
		if(chatProfile.getMessengerNo() == 1) {
			int chatProfileNo  = getKakaoChatProfileNo(chatProfile.getClientID());
			result = chatDeskDao.insertkakaoDB(chat,chatProfileNo);
			// result 가 -1이면 삽입 실패, 리턴
			if(result  == -1)
				return result;
		}

		//2이면 라인
		else if(chatProfile.getMessengerNo() == 2) {
			int chatProfileNo  = getLineChatProfileNo(chatProfile.getClientID());
			result = chatDeskDao.insertlineDB(chat,chatProfileNo);
			// result 가 -1이면 삽입 실패, 리턴
			if(result  == -1)
				return result;
		}

		//3이면 인스타그램
		else if(chatProfile.getMessengerNo() == 3) {
			int chatProfileNo = getInstagramChatProfileNo(chatProfile.getClientID());
			result = chatDeskDao.insertinstagramDB(chat,chatProfileNo);
			// result 가 -1이면 삽입 실패, 리턴
			if(result  == -1)
				return result;
		}

		//4이면 페이스북 메신저
		else if(chatProfile.getMessengerNo() == 4) {
			
			int chatProfileNo = getFaceBookChatProfileNo(chatProfile.getClientID());
			
			result = chatDeskDao.insertfacebookDB(chat,chatProfileNo);
			// result 가 -1이면 삽입 실패, 리턴
			if(result  == -1)
				return result;
		}

		else {
			int chatProfileNo = getWeChatProfileNo(chatProfile.getClientID());
			result = chatDeskDao.wechatDB(chat,chatProfileNo);
			// result 가 -1이면 삽입 실패, 리턴
			if(result  == -1)
				return result;
		}
		
		
		// 에러가 안나면 1을 리턴 할 것이다.
		return result;
	}

	@Override
	public void setLastChatDate(ChatProfile chatProfile) {

		//세미챗에 업데이트
		chatDeskDao.updateLastChatDateToSemiChat(chatProfile);
		
		//클라이언트 DB에 삽입
		//1이면 카카오톡
		if(chatProfile.getMessengerNo() == 1) {
			chatDeskDao.updateLastChatDateToKakao(chatProfile);
		}

		//2이면 라인
		else if(chatProfile.getMessengerNo() == 2) {
			chatDeskDao.updateLastChatDateToLine(chatProfile);
		}

		//3이면 인스타그램
		else if(chatProfile.getMessengerNo() == 3) {
			chatDeskDao.updateLastChatDateToInstagram(chatProfile);
		}

		//4이면 페이스북 메신저
		else if(chatProfile.getMessengerNo() == 4) {
			chatDeskDao.updateLastChatDateToFacebook(chatProfile);
		}
		
		//위챗
		else {
			chatDeskDao.updateLastChatDateToWechat(chatProfile);
		}
	}

	@Override
	public void readChat(ChatProfile chatProfile) {

		chatDeskDao.updateChatRead(chatProfile);
	}

	@Override
	public String getChat(int chatProfileNo) {
		StringBuffer result = new StringBuffer("");
		
		result.append("{\"result\":[");
		ArrayList<Chat> chatList = chatDeskDao.selectChatListByID(chatProfileNo);
		
		if(chatList.size()==0) return "";
		for(int i=0;i<chatList.size();i++) {
			result.append("[{\"value\":\""+chatList.get(i).getFromID()+"\"},");
			result.append("{\"value\":\""+chatList.get(i).getToID()+"\"},");
			result.append("{\"value\":\""+chatList.get(i).getChatContent()+"\"},");
			result.append("{\"value\":\""+chatList.get(i).getChatTIME()+"\"}]");
			

//			System.out.println("포문 테스트 " + i +"번째 : " + chatList.get(i).getChatContent());
			
			if(i!=chatList.size()-1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size()-1).getChatNo()+"\"}");
//		result.append("], \"last\":\"" + chatNo+"\"}");
//		System.out.println(result.toString());
		
		return result.toString();
	}

	@Override
	public int getFaceBookChatProfileNo(String ClientId) {
		return chatDeskDao.selectFaceBookChatProfileNo(ClientId);
	}

	@Override
	public int getInstagramChatProfileNo(String ClientId) {
		return chatDeskDao.selectInstagramChatProfileNo(ClientId);
	}

	@Override
	public int getKakaoChatProfileNo(String ClientId) {
		return chatDeskDao.selectKakaoChatProfileNo(ClientId);
	}

	@Override
	public int getLineChatProfileNo(String ClientId) {
		
		return chatDeskDao.selectLineChatProfileNo(ClientId);
	}

	@Override
	public int getWeChatProfileNo(String ClientId) {
		return chatDeskDao.selectWeChatProfileNo(ClientId);
	}
}
