package board_p;

import etc_p.FileHandler;		
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.BoardService;
import dao_p.BoardDAO;
import dto_p.BoardDTO;
import dto_p.MemberDTO;

public class BoardEditHandler implements BoardService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			MemberDTO sessDto = (MemberDTO) request.getSession().getAttribute("sessDto");
			String upLoadFile = request.getParameter("perFile");
			if(upLoadFile==null) { 
				upLoadFile=new FileHandler(request).uploadFile(request.getPart("perFile"));
			}
			
			BoardDTO dto = new BoardDTO();
			dto.setPerNum(Integer.parseInt(request.getParameter("perNum")));
			dto.setPerTitle(request.getParameter("perTitle"));
			dto.setUserId(sessDto.getUserId());
			dto.setPerContent(request.getParameter("perContent"));
			dto.setPerFile(upLoadFile);
			
			new BoardDAO().editPost(dto);
			
			request.setAttribute("mainUrl", "inc/alert.jsp");
			request.setAttribute("msg", "수정되었습니다.");
			request.setAttribute("goUrl", "BoardList?admin"+sessDto.getAdmin());
			
			System.out.println("BoardEditHandler 수정 결과 실행");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
