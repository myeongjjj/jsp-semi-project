package cart_p;

import dao_p.CartDAO;
import dto_p.CartDTO;
import dto_p.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.CartService;

public class CartDelete implements CartService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));
		MemberDTO sessDto = (MemberDTO)request.getSession().getAttribute("sessDto");
		
		
		
		new CartDAO().delete(no);
		
		request.setAttribute("mainUrl", "inc/alert.jsp");
		request.setAttribute("msg", "삭제되었습니다.");
		request.setAttribute("goUrl", "Cart?admin="+sessDto.getAdmin() );
		
	}

	
}
