package order_p;

import dao_p.OrderDAO;
import dto_p.MemberDTO;
import dto_p.OrderDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.OrderService;

public class OrderConfirm implements OrderService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String orderNum = request.getParameter("orderNum");
		MemberDTO sessDto = (MemberDTO)request.getSession().getAttribute("sessDto");
		int res = (new OrderDAO().confirm(Integer.parseInt(orderNum)));
		if(res == 1) {
			request.setAttribute("msg", "구매 확정되었습니다.");
			request.setAttribute("goUrl", "OrderDetail?deliveryStatus=&start=&end=&admin="+sessDto.getAdmin());
		}else {
			request.setAttribute("msg", "구매 확정이 실패했습니다.");
			request.setAttribute("goUrl", "OrderDetail?deliveryStatus=&start=&end=&admin="+sessDto.getAdmin());
		}
		
	}
		
}
