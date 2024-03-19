package order_p;

import dao_p.OrderDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.OrderService;

public class OrderChange implements OrderService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
String orderNum = request.getParameter("orderNum");
		
		int res = (new OrderDAO().change(Integer.parseInt(orderNum)));
		if(res == 1) {
			//request.setAttribute("mainUrl", "inc/alert.jsp");
			request.setAttribute("msg", "교환요청 되었습니다.");
			request.setAttribute("goUrl", "/firstProj/myPage/MyPageMain");
		}else {
			request.setAttribute("msg", "교환요청이 실패했습니다.");
			request.setAttribute("goUrl", "/firstProj/myPage/MyPageMain");
		}
	}
		
}
