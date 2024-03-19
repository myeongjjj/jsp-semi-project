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

public class BoardAnswerReg implements BoardService{
	
	/*
	 * String uploadFile(Part file, HttpServletRequest request) throws IOException {
	 * return new FileHandler(request).uploadFile(file); }
	 */
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {	
			MemberDTO sessDto = (MemberDTO) request.getSession().getAttribute("sessDto");
			BoardDTO dto = new BoardDTO();
			int perNum = Integer.parseInt(request.getParameter("perNum"));
			dto.setPerAnswer(request.getParameter("perAnswer"));
			dto.setAnswer(request.getParameter("answer"));
			dto.setPerNum(perNum);
			
			//System.out.println(request.getParameter("answer"));
			new BoardDAO().answer(dto);
			
			
			
			    
			request.setAttribute("msg", "답변등록이 되었습니다.");
			request.setAttribute("mainUrl", "inc/alert.jsp");
			request.setAttribute("goUrl", "PostDetails?perNum="+perNum+"&admind="+sessDto.getAdmin());
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
	}
	
}
