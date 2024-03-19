package notice_p;

import etc_p.FileHandler;	
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.NoticeService;
import dao_p.NoticeDAO;
import dto_p.MemberDTO;
import dto_p.NoticeDTO;

public class NoticeEditHandler implements NoticeService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String upLoadFile = request.getParameter("noticeFile");
			if(upLoadFile==null) { 
				upLoadFile=new FileHandler(request).uploadFile(request.getPart("noticeFile"));
			}
			
			NoticeDTO dto = new NoticeDTO();
			dto.setnoticeNum(Integer.parseInt(request.getParameter("noticeNum")));
			dto.setnoticeTitle(request.getParameter("noticeTitle"));
			dto.setnoticeContent(request.getParameter("noticeContent"));
			dto.setnoticeFile(request.getParameter("noticeFile"));
			
			new NoticeDAO().editPost(dto);
			MemberDTO sessDto = (MemberDTO) request.getSession().getAttribute("sessDto");
			request.setAttribute("mainUrl", "inc/alert.jsp");
			request.setAttribute("msg", "수정되었습니다.");
			request.setAttribute("goUrl", "NoticeList?admin="+sessDto.getAdmin());
			
			System.out.println("NoticeEditHandler 수정 결과 실행");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
