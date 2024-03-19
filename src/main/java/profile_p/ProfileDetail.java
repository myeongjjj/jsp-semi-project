package profile_p;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.ProfileService;


public class ProfileDetail implements ProfileService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("ProfileDetail.execute() 실행 : ");
	}

}
