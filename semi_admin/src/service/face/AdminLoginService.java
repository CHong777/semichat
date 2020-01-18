package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.Admin;

public interface AdminLoginService {

	public Admin getAdminParam(HttpServletRequest req);

	public Boolean serviceLogin(Admin admin);

}
