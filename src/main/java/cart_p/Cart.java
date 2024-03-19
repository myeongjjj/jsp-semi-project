package cart_p;

import java.util.ArrayList;

import dao_p.CartDAO;
import dto_p.CartDTO;
import dto_p.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.CartService;



public class Cart implements CartService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		MemberDTO sessDto =  (MemberDTO)request.getSession().getAttribute("sessDto");
		
		ArrayList<CartDTO> data = new CartDAO().list(sessDto.getUserId());
		request.setAttribute("mainData", data);
		
	}
}
