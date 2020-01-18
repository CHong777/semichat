package service.impl.chat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.face.chat.ChatProfileDao;
import dao.impl.chat.ChatProfileDaoImpl;
import dto.ChatProfile;
import service.face.chat.ChatProfileService;
import util.TIME_MAXIMUM;

public class ChatProfileServiceImpl implements ChatProfileService {

	private ChatProfileDao chatProfileDao = new ChatProfileDaoImpl();
	
	@Override
	public Map<String, String> getParam(HttpServletRequest req) {

		//검색조건 담을 맵
		Map<String, String> map = new HashMap<String, String>();

		//전달파라미터 1 검색조건 - 검색알고리즘 사용예정
		String search_str = req.getParameter("search_str");
		map.put("search_str", search_str);
		//search
		
		//전달 파라미터 2 - 메신저 번호들(체크박스)
		String checkedMessengerNoArray = req.getParameter("messengerNoArray");
		map.put("checkedMessengerNoArray", checkedMessengerNoArray);
		//messenger
		
		//전달파라미터 3 - new, 진행중 상태
		String status = req.getParameter("status");
		map.put("status", status);
		
		//전달파라미터 4 - 세션정보
		String counselorid = (String)req.getSession().getAttribute("counselorid");
		map.put("counselorid",counselorid);
		
		return map;
	}
	
	@Override
	public List<ChatProfile> getChatProfileList() {

		//채팅 프로필 리스트 가져오기
		List<ChatProfile> list = chatProfileDao.selectChatProfile();
		
		//날짜 계산해서 불러오기
		List<ChatProfile> rlist = getDiffTime(list);
		
		return rlist;
	}
	
	@Override
	public List<ChatProfile> getChatProfileList(Map<String, String> map) {

		//차단된 프로필 목록 가져오기
		String blockList = chatProfileDao.selectBlockProfile(map);
		
		List<ChatProfile> list = null;
		
		System.out.println("차단" +blockList);
		if(blockList != null && !("").equals(blockList))
			list = chatProfileDao.selectChatProfile(map, blockList);
		else
			//채팅 프로필 리스트 가져오기
			list = chatProfileDao.selectChatProfile(map);
		
		//날짜 계산해서 불러오기
		List<ChatProfile> rlist = getDiffTime(list);
		
		return rlist;
	}
	
	@Override
	public ChatProfile getChatProfileNo(HttpServletRequest req) {

		String param = req.getParameter("chatProfileNo");
		ChatProfile chatProfile = new ChatProfile();
		
		//채팅 프로필 넘버 가져오기
		if(param != null && !"".equals(param)) {
			int chatProfileNo = Integer.parseInt(param);
			chatProfile.setChatProfileNo(chatProfileNo);
		}
		
		return chatProfile;
	}

	@Override
	public void updateStatus(ChatProfile chatProfile) {

		//채팅 진행을 현재상태로 변경
		int status = 1;
		
		chatProfileDao.updateStatus(chatProfile, status);
	}

	@Override
	public List<ChatProfile> getDiffTime(List<ChatProfile> list) {

		Iterator<ChatProfile> iter = list.iterator();
		while(iter.hasNext()) {
			try {
				
				ChatProfile chatProfile = iter.next();
				
				SimpleDateFormat fm = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
				Date strdate = fm.parse(chatProfile.getLastChatTime());
				
				//날짜 차이
				String diff = TIME_MAXIMUM.formatTimeString(strdate);
				chatProfile.setLastChatTime(diff);
						
				System.out.println(strdate);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public void closeTab(ChatProfile chatProfile) {

		//완료상태
		int status = 2;

		//채팅 프로필 상태를 완료상태로
		chatProfileDao.updateStatus(chatProfile, status);
		
	}
}
