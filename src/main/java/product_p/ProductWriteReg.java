package product_p;

import java.io.File;	
import java.io.IOException;

import dao_p.ProductDAO;
import dto_p.MemberDTO;
import dto_p.ProductDTO;
import etc_p.FileHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service_p.ProductService;

public class ProductWriteReg implements ProductService{
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
			try {
				String upLoadFile = new FileHandler(request).uploadFile(request.getPart("prodFile"));
				ProductDTO dto = new ProductDTO();
				dto.setProdFile(upLoadFile);
				dto.setProdCate(request.getParameter("prodCate"));
				dto.setProdTitle(request.getParameter("prodTitle"));
				dto.setProdPrice(Integer.parseInt(request.getParameter("prodPrice")));
				dto.setProdInfo(request.getParameter("prodInfo"));

				new ProductDAO().write(dto);
				MemberDTO sessDto = (MemberDTO) request.getSession().getAttribute("sessDto");
				
				request.setAttribute("mainUrl", "inc/alert.jsp");
				request.setAttribute("msg","상품이 등록되었습니다.");
				request.setAttribute("goUrl", "/firstProj/main/Main?admin="+sessDto.getAdmin());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
