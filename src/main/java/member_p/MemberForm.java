package member_p;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.MemberService;

public class MemberForm implements MemberService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("멤버폼");
		
	}

}
