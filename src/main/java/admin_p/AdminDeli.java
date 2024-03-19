package admin_p;

import java.util.ArrayList;

import dao_p.AdminDAO;
import dto_p.AdminDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.AdminService;

public class AdminDeli implements AdminService {
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String orderNum = request.getParameter("orderNum");
		ArrayList<AdminDTO> adDeliData = new AdminDAO().adDeliList(orderNum);
		ArrayList<AdminDTO> deliProdData = new AdminDAO().adDeliProd(orderNum);
		request.setAttribute("adDeliData", adDeliData);
		request.setAttribute("deliProdData", deliProdData);
		
	}
}
