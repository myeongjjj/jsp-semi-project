package admin_p;



import dao_p.AdminDAO;
import dto_p.AdminDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.AdminService;

public class AdminDeliReg implements AdminService {
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		AdminDTO dto = new AdminDTO();
		dto.setOrderNum(Integer.parseInt(request.getParameter("orderNum")));
		dto.setDeliveryStatus(request.getParameter("deliveryStatus"));
		dto.setWayBill(request.getParameter("wayBill"));
		dto.setOrderStatus(request.getParameter("orderStatus"));
		
		new AdminDAO().deliModify(dto);
		
		request.setAttribute("mainUrl", "inc/alert.jsp");
		request.setAttribute("msg", "수정되었습니다.");
		request.setAttribute("goUrl", "Admin?admin=0" );
		
	}
}
