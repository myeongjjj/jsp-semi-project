package product_p;

import java.util.ArrayList;

import dao_p.ProductDAO;
import dao_p.ReviewDAO;
import dto_p.ProductDTO;
import dto_p.ReviewDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.ProductService;



public class ProductList implements ProductService {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("상품페이지");
		ArrayList<ProductDTO> data = new ProductDAO().list(request.getParameter("prodCate"));
		request.setAttribute("mainData", data);
	
		
	}
}
