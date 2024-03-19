package product_p;

import java.util.ArrayList;

import dao_p.ReviewDAO;
import dto_p.MemberDTO;
import dto_p.ReviewDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.ProductService;



public class ReviewDetail implements ProductService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//int prodNum = Integer.parseInt(request.getParameter("prodNum"));
		MemberDTO sessDto = (MemberDTO)request.getSession().getAttribute("sessDto");
	
		ArrayList<ReviewDTO> reDto = new ReviewDAO().detail(sessDto.getUserId());
		request.setAttribute("reDto", reDto);

		
	}
}
