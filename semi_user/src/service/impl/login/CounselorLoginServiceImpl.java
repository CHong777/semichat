package service.impl.login;

import javax.servlet.http.HttpServletRequest;

import dao.face.login.CounselorLoginDao;
import dao.impl.login.CounselorLoginDaoImpl;
import dto.Counselor;
import service.face.login.CounselorLoginService;

public class CounselorLoginServiceImpl implements CounselorLoginService{
	
	//CounselorLoginDao 객체
	private CounselorLoginDao counselorloginDao = new CounselorLoginDaoImpl();
	
	@Override
	public Counselor getCounselorParam(HttpServletRequest req) {
		
		Counselor counselor = new Counselor();
		
		counselor.setCounselorId(req.getParameter("counselorid"));
		counselor.setCounselorPassword(req.getParameter("counselorpassword"));
		
		return counselor;
	}

	@Override
	public boolean login(Counselor counselor) {
		
		if(counselorloginDao.selectCntCounselorByCounseloridAndCounselorpassword(counselor) >=1 ) {
			//로그인 성공
			return true;
			
		} else {
			//로그인 실패
			return false;
		}
		
	}

	@Override
	public Counselor getCounselorByCounselorid(Counselor counselor) {
		
		return counselorloginDao.selectCounselorByCounselorid(counselor);
	}




	
}
