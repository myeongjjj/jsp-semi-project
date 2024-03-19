package product_p;


import dao_p.ReviewDAO;
import dto_p.MemberDTO;
import dto_p.ReviewDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.ProductService;

public class ReviewWriteReg implements ProductService{
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		ReviewDTO dto = new ReviewDTO();
		MemberDTO sessDto = (MemberDTO)request.getSession().getAttribute("sessDto");
		dto.setProdNum(Integer.parseInt(request.getParameter("prodNum")));
		dto.setReviewTitle(request.getParameter("reviewTitle"));
		dto.setUserId(sessDto.getUserId());
		dto.setReviewStar(Integer.parseInt(request.getParameter("reviewStar")));


	
		new ReviewDAO().write(dto);
		
		
		request.setAttribute("mainUrl", "inc/alert.jsp");
		request.setAttribute("msg","리뷰가 등록되었습니다	.");
		request.setAttribute("goUrl", "ProductDetail?prodNum="+request.getParameter("prodNum")+"&admin="+sessDto.getAdmin());
		
	}
}
