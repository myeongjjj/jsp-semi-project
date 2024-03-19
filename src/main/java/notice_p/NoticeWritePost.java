package notice_p;

import jakarta.servlet.http.HttpServletRequest;			
import jakarta.servlet.http.HttpServletResponse;
import service_p.NoticeService;
import dao_p.NoticeDAO;
import dto_p.NoticeDTO;

public class NoticeWritePost implements NoticeService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("NoticewritePost execute() : 게시판 글쓰기 실행");
	}
}
