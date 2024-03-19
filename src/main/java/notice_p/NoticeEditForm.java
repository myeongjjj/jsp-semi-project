package notice_p;

import jakarta.servlet.http.HttpServletRequest;	
import jakarta.servlet.http.HttpServletResponse;
import service_p.NoticeService;
import dao_p.NoticeDAO;
import dto_p.NoticeDTO;;

public class NoticeEditForm implements NoticeService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		NoticeDTO dto = new NoticeDAO().getPostDetails(noticeNum);
		request.setAttribute("dto", dto);
		
		
		
	}
}
