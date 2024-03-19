package board_p;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service_p.BoardService;
import dao_p.BoardDAO;
import dto_p.BoardDTO;
import dto_p.MemberDTO;


public class BoardList implements BoardService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO sessDto =  (MemberDTO)request.getSession().getAttribute("sessDto");
		System.out.println("BoardList execute() 실행");
		String cate = request.getParameter("searchCate");
		String search = request.getParameter("search");
		ListPager page = new ListPager(request);
		
		if(search!=null) {
			ArrayList<BoardDTO> searchResult = new BoardDAO().serachList(cate, search);
			request.setAttribute("searchResult", searchResult);
			request.getAttribute("searchResult");
			
			if(!cate.equals("")&&search.equals("")) {
				request.setAttribute("mainUrl", "include/alert.jsp");
				request.setAttribute("msg", "검색어를 입력해주세요.");
				request.setAttribute("goUrl", "BoardList?admin="+sessDto.getAdmin());
			}
		} else {
			
			ArrayList<BoardDTO> boardDataList = new BoardDAO().list(page, sessDto.getUserId(), sessDto.getAdmin());
			request.setAttribute("page", page);
			request.setAttribute("boardDataList", boardDataList);
		}
	}
}