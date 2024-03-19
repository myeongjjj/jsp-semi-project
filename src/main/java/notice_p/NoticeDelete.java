package notice_p;

import java.util.logging.FileHandler;	

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.NoticeService;
import dao_p.NoticeDAO;
import dto_p.MemberDTO;
import dto_p.NoticeDTO;

public class NoticeDelete implements NoticeService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
		NoticeDTO dto = new NoticeDAO().getPostDetails(noticeNum);
		new NoticeDAO().deletePost(noticeNum);
		
		if(dto.getnoticeFile()!=null) {
			new etc_p.FileHandler(request).deleteFile(dto.getnoticeFile());
		}
		MemberDTO sessDto = (MemberDTO) request.getSession().getAttribute("sessDto");
		request.setAttribute("mainUrl", "inc/alert.jsp");
		request.setAttribute("goUrl", "NoticeList?admin="+sessDto.getAdmin());
		request.setAttribute("msg", "삭제되었습니다.");
	}
}
