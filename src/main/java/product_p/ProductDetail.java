package product_p;

import java.util.ArrayList;

import dao_p.ProductDAO;
import dao_p.ReviewDAO;
import dto_p.ProductDTO;
import dto_p.ReviewDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.ProductService;



public class ProductDetail implements ProductService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int prodNum = Integer.parseInt(request.getParameter("prodNum"));
		ProductDTO dto = new ProductDAO().detail(prodNum);
		
		ArrayList<ReviewDTO> reData = new ReviewDAO().list(prodNum);
		request.setAttribute("dto", dto);
		request.setAttribute("reviewData", reData);
		
	}
}
