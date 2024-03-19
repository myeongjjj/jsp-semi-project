package myPage_p;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.MyPageService;



public class MyPageMain implements MyPageService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("마이페이지");
		
	}

}
