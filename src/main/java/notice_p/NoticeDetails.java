package notice_p;

import jakarta.servlet.http.HttpServletRequest;	
import jakarta.servlet.http.HttpServletResponse;
import service_p.NoticeService;
import board_p.NoticeListPager;
import dao_p.NoticeDAO;
import dto_p.NoticeDTO;

public class NoticeDetails implements NoticeService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//상세보기(db 번호)
		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		NoticeDTO dto = new NoticeDAO().getPostDetails(noticeNum);
		//전체 행의 갯수
		NoticeListPager page = new NoticeListPager(request);
		int total = new NoticeDAO().noticeTotal();
		
		request.setAttribute("total", total);
		request.setAttribute("dto", dto);
		System.out.println("postDetails() 실행 : 게시물 자세히보기" + dto);
		
		
	}
	
}
