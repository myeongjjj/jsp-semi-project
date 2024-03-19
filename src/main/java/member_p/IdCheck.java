package member_p;

import java.io.IOException;

import dao_p.MemberDAO;
import dto_p.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.MemberService;

public class IdCheck implements MemberService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		  MemberDTO dto = new MemberDTO();
		  String userId = request.getParameter("userId");
		  boolean idChk = new MemberDAO().idExist(userId);
		  request.setAttribute("idChk", idChk);
		  request.setAttribute("userId", userId);
		 
		
		 System.out.println("id 중복체크 중 =="+idChk+","+userId); 
	
		 try {
			response.getWriter().append(idChk+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
