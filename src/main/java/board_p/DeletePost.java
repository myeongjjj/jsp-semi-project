package board_p;

import java.util.logging.FileHandler;	

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.BoardService;
import dao_p.BoardDAO;
import dto_p.BoardDTO;
import dto_p.MemberDTO;

public class DeletePost implements BoardService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int perNum = Integer.parseInt(request.getParameter("perNum"));
		BoardDTO dto = new BoardDAO().getPostDetails(perNum);
		new BoardDAO().deletePost(perNum);
		
		if(dto.getPerFile()!=null) {
			new etc_p.FileHandler(request).deleteFile(dto.getPerFile());
		}
		MemberDTO sessDto = (MemberDTO) request.getSession().getAttribute("sessDto");
		request.setAttribute("mainUrl", "inc/alert.jsp");
		request.setAttribute("goUrl", "BoardList?admin="+sessDto.getAdmin());
		request.setAttribute("msg", "삭제되었습니다.");
	}
}
