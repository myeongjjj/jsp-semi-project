package profile_p;

import java.util.ArrayList;

import dao_p.ProfileDAO;
import dto_p.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.ProfileService;


public class ProfileModify implements ProfileService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO dto = (MemberDTO)request.getSession().getAttribute("sessDto");
		dto.setPw(request.getParameter("pw"));
		dto.setEmail(request.getParameter("email"));
		dto.setTel(request.getParameter("tel"));
		dto.setUserId(dto.getUserId());
		dto.setAddr(request.getParameter("addr1")+" "+request.getParameter("addr2"));
		new ProfileDAO().modify(dto);
		
		request.setAttribute("mainUrl", "inc/alert.jsp");
		request.setAttribute("msg", "수정 완료되었습니다.");
		request.setAttribute("goUrl", "/firstProj/myPage/MyPageMain?userId="+dto.getUserId()+"&admin="+dto.getAdmin());
	}
	
}
