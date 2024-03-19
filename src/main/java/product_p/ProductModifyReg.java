package product_p;

import dao_p.ProductDAO;
import dto_p.MemberDTO;
import dto_p.ProductDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.ProductService;

public class ProductModifyReg implements ProductService{
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		ProductDTO dto = new ProductDTO();
		dto.setProdCate(request.getParameter("prodCate"));
		dto.setProdFile(request.getParameter("prodFile"));
		dto.setProdTitle(request.getParameter("prodTitle"));
		dto.setProdPrice(Integer.parseInt(request.getParameter("prodPrice")));
		dto.setProdInfo(request.getParameter("prodInfo"));
		dto.setProdNum(Integer.parseInt(request.getParameter("prodNum")));
		
		System.out.println(request.getParameter("prodNum"));
		System.out.println(request.getParameter("prodFile"));
	
		new ProductDAO().modify(dto);
		
		MemberDTO sessDto = (MemberDTO) request.getSession().getAttribute("sessDto");
		request.setAttribute("mainUrl", "inc/alert.jsp");
		request.setAttribute("msg","수정되었습니다.");
		request.setAttribute("goUrl", "ProductDetail?admin="+sessDto.getAdmin()+"&prodNum="+Integer.parseInt(request.getParameter("prodNum")));
		
	}
}
