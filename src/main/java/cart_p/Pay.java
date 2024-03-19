package cart_p;

import java.util.ArrayList;

import dao_p.CartDAO;
import dto_p.CartDTO;
import dto_p.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.CartService;



public class Pay implements CartService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO sessDto =  (MemberDTO)request.getSession().getAttribute("sessDto");
		
		String [] select = request.getParameter("select").split(",");
		
		//ArrayList<CartDTO> data = new CartDAO().payList(sessDto.getUserId(),no);
		//request.setAttribute("mainData", data);
		  ArrayList<CartDTO> dataList = new ArrayList<CartDTO>();
	     
	            for (String sel : select) {
	                int itemNo = Integer.parseInt(sel);
	                ArrayList<CartDTO> data = new CartDAO().payList(sessDto.getUserId(), itemNo);
	                dataList.addAll(data);
	            }
	        
	        request.setAttribute("mainData", dataList); 
	      
	}
}
