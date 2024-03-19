package product_p;


import java.util.ArrayList;

import dao_p.ProductDAO;
import dao_p.ReviewDAO;
import dto_p.MemberDTO;
import dto_p.ProductDTO;
import dto_p.ReviewDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.ProductService;



public class ReviewDelete implements ProductService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int reviewNum = Integer.parseInt(request.getParameter("reviewNum"));
		String urserId = request.getParameter("userId");
		ArrayList<ReviewDTO> reDto = new ReviewDAO().detail(urserId);
		System.out.println(request.getParameter("reviewNum"));
		new ReviewDAO().delete(reviewNum);
		request.setAttribute("reDto", reDto);
		MemberDTO sessDto = (MemberDTO) request.getSession().getAttribute("sessDto");
		
		
		request.setAttribute("mainUrl", "inc/alert.jsp");
		request.setAttribute("msg", "삭제되었습니다.");
		request.setAttribute("goUrl", "ReviewDetail?userId="+request.getParameter("userId")+"&admin="+sessDto.getAdmin());
		
		
	}
}
