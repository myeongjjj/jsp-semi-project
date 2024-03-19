package logout_p;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service_p.MemberService;

public class LogOut implements MemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		request.setAttribute("mainUrl", "inc/alert.jsp");
	    request.setAttribute("msg","로그아웃되었습니다.");
		request.setAttribute("goUrl", "/firstProj/main/Main");
	
		
	}

}
