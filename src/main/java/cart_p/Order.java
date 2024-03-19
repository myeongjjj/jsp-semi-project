package cart_p;

import java.util.ArrayList;

import dao_p.CartDAO;
import dto_p.CartDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.CartService;

public class Order implements CartService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		CartDTO dto = new CartDTO();
//		dto.setProdNum(Integer.parseInt(request.getParameter("orderNum")));
//		dto.setUserId(request.getParameter("userId"));
		String [] selects = request.getParameter("select").split(",");
		String select = request.getParameter("select");
		
//		new CartDAO().orderinfo(dto);
		
		CartDTO dto2 = new CartDTO();
		dto2.setProdNum(Integer.parseInt(request.getParameter("orderNum")));
		dto2.setReceiver(request.getParameter("receiver"));
		dto2.setPostNum(request.getParameter("postcode"));
		dto2.setOrderAddress(request.getParameter("addr"));    
		dto2.setPhoneNum(request.getParameter("phoneNum"));
		dto2.setMessage(request.getParameter("message")); 
		dto2.setUserId(request.getParameter("userId")); 
		new CartDAO().delivery(dto2);

		
	     
	            for (String sel : selects) {
	                int no = Integer.parseInt(sel);
	                dto.setProdNum(Integer.parseInt(request.getParameter("orderNum")));
	        		dto.setUserId(request.getParameter("userId"));
	        		new CartDAO().orderinfo(dto,no);
	        		
	                new CartDAO().orderDelete(request.getParameter("userId"),no);
	                
	               
	            }
		
		//new CartDAO().orderDelete(request.getParameter("userId"),Integer.parseInt(request.getParameter("select")));
		
		request.setAttribute("mainUrl", "inc/alert.jsp");
		request.setAttribute("msg", "결제되었습니다.");
		//request.setAttribute("goUrl", "/firstProj/admin/Admin" );
		request.setAttribute("goUrl", "/firstProj/order/OrderDetail?select="+select+"&deliveryStatus=&start=&end=" );
	}
	
}
