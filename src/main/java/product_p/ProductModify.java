package product_p;

import dao_p.ProductDAO;
import dto_p.ProductDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.ProductService;



public class ProductModify implements ProductService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int prodNum = Integer.parseInt(request.getParameter("prodNum"));
		ProductDTO dto = new ProductDAO().detail(prodNum);
		request.setAttribute("dto", dto);
		
		
	}
}
