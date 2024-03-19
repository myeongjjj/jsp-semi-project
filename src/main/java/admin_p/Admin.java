package admin_p;

import java.util.ArrayList;

import dao_p.AdminDAO;
import dto_p.AdminDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.AdminService;

public class Admin implements AdminService {
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		int tData = new AdminDAO().total(start, end);
		ArrayList<AdminDTO> data = new AdminDAO().list(start, end);
		ArrayList<AdminDTO> orderData = new AdminDAO().orderList();
		request.setAttribute("mainData", data);
		request.setAttribute("orderData", orderData);
		request.setAttribute("tData", tData);
		
	}
}
