package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import util.Paging;

public interface MemberManageService {

	public Paging getPaging(HttpServletRequest req, int flag);

	public List getList(Paging paging, int flag);


}
