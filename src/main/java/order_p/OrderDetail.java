package order_p;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dao_p.OrderDAO;
import dto_p.MemberDTO;
import dto_p.OrderDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.OrderService;


public class OrderDetail implements OrderService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO sessDto = (MemberDTO)request.getSession().getAttribute("sessDto");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String deliveryStatus = request.getParameter("deliveryStatus");
		
		String userId = sessDto.getUserId();
		System.out.println(deliveryStatus+","+ start +","+ end +","+ userId);
		ArrayList<OrderDTO> data = new OrderDAO().detailList(userId, start, end, deliveryStatus);
		
		Calendar cal = Calendar.getInstance();
		//System.out.println(new Date(cal.getTimeInMillis()));
		cal.add(Calendar.DATE, -7);
		//System.out.println(new Date(cal.getTimeInMillis()));
		//System.out.println(today);
		for (OrderDTO dto : data) {
			if(dto.getOrderDate().before(new Date(cal.getTimeInMillis()))&&dto.getDeliveryStatus().equals("배송완료")) {
				new OrderDAO().autoConfirm(dto.getOrderNum());
				data = new OrderDAO().detailList(userId, start, end, deliveryStatus);
			}
		}
		request.setAttribute("mainData", data);
		System.out.println("OrderDetail.execute() 실행 : ");
		
	}
	
}
