package board_p;

import jakarta.servlet.http.HttpServletRequest;	
import jakarta.servlet.http.HttpServletResponse;
import service_p.BoardService;

import dao_p.BoardDAO;
import dto_p.BoardDTO;

public class PostDetails implements BoardService{
	@Override
public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int perNum = Integer.parseInt(request.getParameter("perNum"));
		BoardDTO dto = new BoardDAO().getPostDetails(perNum);
		ListPager page = new ListPager(request);
		page.totalCalc(new BoardDAO().boardTotal());
		
		int total = new BoardDAO().boardTotal();
		request.setAttribute("total", total);
		
		request.setAttribute("page", page);
		request.setAttribute("dto", dto);
		System.out.println("postDetails() 실행 : 게시물 자세히보기" + dto);			
			
	}
}
