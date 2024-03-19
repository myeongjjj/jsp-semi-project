package board_p;

import jakarta.servlet.http.HttpServletRequest;	
import jakarta.servlet.http.HttpServletResponse;
import service_p.BoardService;
import dao_p.BoardDAO;
import dto_p.BoardDTO;

public class WritePost implements BoardService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("writePost execute() : 게시판 글쓰기 실행");
	}
}
