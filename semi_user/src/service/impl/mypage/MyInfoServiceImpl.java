package service.impl.mypage;

import javax.servlet.http.HttpServletRequest;

import dao.face.mypage.MyInfoDao;
import dao.impl.mypage.MyInfoDaoImpl;
import dto.Counselor;
import service.face.mypage.MyInfoService;

public class MyInfoServiceImpl implements MyInfoService{

	MyInfoDao myInfoDao = new MyInfoDaoImpl();
	
	@Override
	public String getPassword(String id) {
		return myInfoDao.selectPassword(id);
		
	}


	@Override
	public Counselor getCounselorInfo(Counselor counselor) {
		return myInfoDao.selectCounselorInfo(counselor);
	}

	@Override
	public Counselor getCounselorInfo(HttpServletRequest req) {
		Counselor counselor = new Counselor();
		
		String name = req.getParameter("name");
    	String nickName = req.getParameter("nickName");
    	String phoneNumber = req.getParameter("phoneNumber");
    	String password = req.getParameter("password");
		
		counselor.setCounselorName(name);
		counselor.setCounselorNickname(nickName);
		counselor.setCounselorPassword(password);
		counselor.setCounselorPhonenumber(phoneNumber);
		
		return counselor;
	}

	@Override
	public void counselorInfoUpdate(Counselor counselor) {
		myInfoDao.UpdateCounselorInfo(counselor);
	}


	@Override
	public int counselorNicknameCheck(String nickname) {
		return myInfoDao.selectNicknameForCheck(nickname);
	}


	@Override
	public void counselorWithdrawalUpdate(Counselor counselor) {
		myInfoDao.updateCounselorWithdrawalCheck(counselor);
		
	}





}
