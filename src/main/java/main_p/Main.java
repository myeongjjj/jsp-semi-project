package main_p;

import java.util.ArrayList;

import dao_p.MainDAO;
import dto_p.MainDTO;
import dto_p.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service_p.MainService;

public class Main implements MainService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
//		PPData pData = new PPData(request);
//		pData.totalCalc(new BoardDAO().total());
//		ArrayList<BoardDTO> data =  new BoardDAO().list(pData);
//		request.setAttribute("mainData", data);
//		request.setAttribute("pData", pData);
		HttpSession session = request.getSession();
	    System.out.println(session.getAttribute("userId"));
		ArrayList<MainDTO> data = new MainDAO().list();
		request.setAttribute("mainData", data);
	        
//		System.out.println("main.excute() 실행");
	}

}
