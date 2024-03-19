package notice_p;

import jakarta.servlet.http.HttpServletRequest;		
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service_p.NoticeService;
import dao_p.NoticeDAO;
import dto_p.MemberDTO;
import dto_p.NoticeDTO;

import java.io.File;
import java.io.IOException;

import etc_p.FileHandler;

public class NoticeWritePostHandler implements NoticeService{
	
	/*
	 * String uploadFile(Part file, HttpServletRequest request) throws IOException {
	 * return new FileHandler(request).uploadFile(file); }
	 */
	String uploadFile(Part file, HttpServletRequest request) throws IOException {
		if(!file.getSubmittedFileName().equals("")) {//파일명이 빈칸이 아닐때만 저장함
			
			String fileName = file.getSubmittedFileName();
			int pos = fileName.lastIndexOf(".");
			String fDomaine = fileName.substring(0, pos);
			String ext = fileName.substring(pos);
			int cnt=1;
			
			
			String dir = request.getServletContext().getRealPath("saveFile/");
			dir = "/firstProj/src/main/webapp/saveFile";
			
			File nowFile = new File(dir+fileName);
				while(nowFile.exists()) {//같은 이름의 파일이 이미 있을 경우
					fileName = fDomaine+"_"+cnt+ext;
					nowFile = new File(dir+fileName);
					cnt++;
				}
				
				file.write(dir+fileName);
				file.delete();
				
				return fileName;
				}
			return null;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {	
			String upLoadFile = new FileHandler(request).uploadFile(request.getPart("noticeFile"));
			
			NoticeDTO dto = new NoticeDTO();
			dto.setnoticeTitle(request.getParameter("writeTitle"));
			dto.setnoticeContent(request.getParameter("writeContent"));
			dto.setnoticeFile(upLoadFile);
			MemberDTO sessDto = (MemberDTO) request.getSession().getAttribute("sessDto");
			if (!request.getParameter("writeTitle").trim().isEmpty() && !request.getParameter("writeContent").trim().isEmpty()) {
			    new NoticeDAO().writePost(dto);
			    request.setAttribute("msg", "등록되었습니다.");
				request.setAttribute("mainUrl", "inc/alert.jsp");
				request.setAttribute("goUrl", "NoticeList?admin="+sessDto.getAdmin());
			}else{
				request.setAttribute("mainUrl", "inc/alert.jsp");
			    request.setAttribute("msg", "작성하지 않은 필수 항목이 있습니다.");
				request.setAttribute("goUrl", "NoticeWritePost");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
	}
	
}
