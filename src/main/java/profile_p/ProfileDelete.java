package profile_p;

import dao_p.ProfileDAO;
import dto_p.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service_p.ProfileService;

public class ProfileDelete implements ProfileService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO dto = (MemberDTO)request.getSession().getAttribute("sessDto");
		
		new ProfileDAO().delete(dto.getUserId());
		HttpSession session = request.getSession();
		session.invalidate();
		request.setAttribute("mainUrl", "inc/alert.jsp");
		request.setAttribute("msg", "회원 탈퇴되었습니다.");
		request.setAttribute("goUrl", "/firstProj/main/Main");
		
	}
	
}
