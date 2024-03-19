package product_p;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.ProductService;

public class ProductWrite implements ProductService{
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
	
		System.out.println("글쓰기");
		
	}
}
