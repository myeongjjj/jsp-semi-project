package cart_p;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import dao_p.CartDAO;
import dto_p.CartDTO;
import dto_p.MemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service_p.CartService;



public class CartReg implements CartService{
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//ArrayList<BoardDTO> data = new BoardDAO().list();
		//request.setAttribute("mainData", data);
		
		try {
			MemberDTO sessDto = (MemberDTO)request.getSession().getAttribute("sessDto");
			
			
			CartDTO dto = new CartDTO();
			dto.setCartFile(request.getParameter("cartFile"));
			dto.setCartTitle(request.getParameter("cartTitle"));
			dto.setUserId(request.getParameter("userId"));
			dto.setOption1(request.getParameter("option1"));
			dto.setOption2(request.getParameter("option2"));
			dto.setProdPrice(Integer.parseInt(request.getParameter("prodPrice")));
			dto.setProdCnt(Integer.parseInt(request.getParameter("prodCnt")));
			dto.setProdNum(Integer.parseInt(request.getParameter("prodNum")));
			new CartDAO().cartAdd(dto);
		
			if(request.getParameter("chk").equals("true")) {
				request.setAttribute("mainUrl", "inc/alert.jsp");
				request.setAttribute("msg", "장바구니로 이동합니다.");
				request.setAttribute("goUrl", "Cart?admin="+sessDto.getAdmin());
			}else if(request.getParameter("chk").equals("false")) {
				request.setAttribute("mainUrl", "inc/alert.jsp");
				request.setAttribute("msg", "장바구니에 저장되었습니다.");
				request.setAttribute("goUrl", "/firstProj/product/ProductDetail?prodNum="+request.getParameter("prodNum")+"&admin="+sessDto.getAdmin());
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
