package service.impl;

import javax.servlet.http.HttpServletRequest;

import dao.face.AdminLoginDao;
import dao.impl.AdminLoginDaoImpl;
import dto.Admin;
import service.face.AdminLoginService;

public class AdminLoginServiceImpl implements AdminLoginService {

	AdminLoginDao adminLoginDao = new AdminLoginDaoImpl();
	@Override
	public Admin getAdminParam(HttpServletRequest req) {
		Admin admin =  new Admin();
		String param=null;
		
		// adminId
		param=req.getParameter("adminId");
		System.out.println(param);
		if(param!=null && !"".equals(param)) {
			admin.setAdminId(param);
		}
		
		// adminPw
		param=req.getParameter("adminPw");
		if(param!=null && !"".equals(param)) {
			admin.setAdminPw(param);
		}
		return admin;
	}

	@Override
	public Boolean serviceLogin(Admin admin) {
		System.out.println("서비스 "+admin);
		if(adminLoginDao.login(admin) >=1 ) {
			//로그인 성공
			return true;
		} else {
			//로그인 실패
			return false;
		}
	}

}
