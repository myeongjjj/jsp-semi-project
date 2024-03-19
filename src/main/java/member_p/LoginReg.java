package member_p;

import dao_p.MemberDAO;
import dto_p.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import service_p.MemberService;

public class LoginReg implements MemberService{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
	      //System.out.println("실행좀해봐");
	      String userId = request.getParameter("userId");
	      String pw = request.getParameter("pw");
	      MemberDTO sessDto = new MemberDAO().loginChk(userId, pw);
	      
	      if(sessDto != null  && sessDto.getStatus()==1) {
	          request.getSession().setAttribute("sessDto", sessDto);
	               
	         request.setAttribute("mainUrl", "inc/alert.jsp");
	          request.setAttribute("msg",sessDto.getUserName()+"님 로그인되었습니다.");
	         request.setAttribute("goUrl", "/firstProj/main/Main?userId="+sessDto.getUserId()+"&admin="+sessDto.getAdmin()+"&status="+sessDto.getStatus());
	      
	      }else {
	         request.setAttribute("mainUrl", "inc/alert.jsp");
	          request.setAttribute("msg", "로그인 실패되었습니다.");
	         request.setAttribute("goUrl", "/firstProj/member/LoginForm");
	      }
	      
	   }
}
