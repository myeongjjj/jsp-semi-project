package board_p;

import jakarta.servlet.http.HttpServletRequest;			
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service_p.BoardService;
import dao_p.BoardDAO;
import dto_p.BoardDTO;
import dto_p.MemberDTO;

import java.io.File;
import java.io.IOException;

import etc_p.FileHandler;

public class WritePostHandler implements BoardService{
	
	/*
	 * String uploadFile(Part file, HttpServletRequest request) throws IOException {
	 * return new FileHandler(request).uploadFile(file); }
	 */
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {	
			String upLoadFile = new FileHandler(request).uploadFile(request.getPart("perFile"));
			
			BoardDTO dto = new BoardDTO();
			dto.setBoardCate(request.getParameter("boardCate"));
			dto.setPerTitle(request.getParameter("writeTitle"));
			dto.setPerContent(request.getParameter("writeContent"));
			dto.setPerFile(upLoadFile);
			MemberDTO sessDto = (MemberDTO) request.getSession().getAttribute("sessDto");
			
			if (!request.getParameter("writeTitle").trim().isEmpty() && !request.getParameter("writeContent").trim().isEmpty()) {
			    new BoardDAO().writePost(dto,sessDto.getUserId());
			    
			    request.setAttribute("msg", "등록되었습니다.");
				request.setAttribute("mainUrl", "inc/alert.jsp");
				request.setAttribute("goUrl", "BoardList?admin="+sessDto.getAdmin());
			}else{
				request.setAttribute("mainUrl", "inc/alert.jsp");
			    request.setAttribute("msg", "작성하지 않은 필수 항목이 있습니다.");
				request.setAttribute("goUrl", "WritePost?admin ="+sessDto.getAdmin());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
	}
	
}
