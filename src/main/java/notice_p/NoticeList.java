package notice_p;

import java.util.ArrayList;

import board_p.NoticeListPager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao_p.NoticeDAO;
import dto_p.NoticeDTO;

import service_p.NoticeService;

public class NoticeList implements NoticeService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("NoticeList execute() 실행");
		String search = request.getParameter("search");
		NoticeListPager page = new NoticeListPager(request);
		
		if(search!=null) {

			ArrayList<NoticeDTO> searchResult = new NoticeDAO().serachList(search);
			request.setAttribute("searchResult", searchResult);
			request.getAttribute("searchResult");
			
			if(search.equals("")) {
				request.setAttribute("mainUrl", "include/alert.jsp");
				request.setAttribute("msg", "검색어를 입력해주세요.");
				request.setAttribute("goUrl", "BoardList");
			}
		} else {
			ArrayList<NoticeDTO> NoticeDataList = new NoticeDAO().list(page);
			request.setAttribute("page", page);
			request.setAttribute("NoticeDataList", NoticeDataList);
		}
	}
	
}