package member_p;


import dao_p.MemberDAO;
import dto_p.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service_p.MemberService;

public class MemberWrite implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {

			MemberDTO dto = new MemberDTO();
			dto.setUserId(request.getParameter("userId"));
			dto.setPw(request.getParameter("pw"));
			dto.setUserName(request.getParameter("userName"));
			dto.setGender(request.getParameter("gender"));
			dto.setTel(request.getParameter("tel"));
			dto.setEmail(request.getParameter("email"));
			dto.setAddr(request.getParameter("addr"));
			dto.setPostNum(request.getParameter("postNum"));
			
			
			new MemberDAO().write(dto);
			
			System.out.println("MemberWrite.execute() 실행:");
			
			request.setAttribute("mainUrl", "inc/alert.jsp");
			request.setAttribute("msg", "회원가입 되었습니다.");
			request.setAttribute("goUrl", "/firstProj/main/Main");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
