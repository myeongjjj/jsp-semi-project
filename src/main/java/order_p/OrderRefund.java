package order_p;

import java.util.ArrayList;

import dao_p.OrderDAO;
import dto_p.MemberDTO;
import dto_p.OrderDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.OrderService;


public class OrderRefund implements OrderService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO sessDto = (MemberDTO)request.getSession().getAttribute("sessDto");
		String orderStatus = request.getParameter("orderStatus");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		String userId = sessDto.getUserId();
		ArrayList<OrderDTO> data = new OrderDAO().refundList(userId, start, end,orderStatus);
		request.setAttribute("mainData", data);
		//request.setAttribute("pData", pData);
		
		System.out.println("OrderRefund.execute() 실행 : ");
		
	}
	
}
