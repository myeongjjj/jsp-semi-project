package dto_p;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoticeDTO {
	private int noticeNum;//게시물 번호
	private String noticeTitle;//제목
	private String userId;//작성자
	private Date noticeDate;//작성일시
	private String noticeContent;//내용
	private String noticeFile;//파일 업로드
	
	public int getnoticeNum() {
		return noticeNum;
	}
	public void setnoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
	}
	public String getnoticeTitle() {
		return noticeTitle;
	}
	public void setnoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getnoticeDate() {
		return noticeDate;
	}
	public void setnoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}
	public String getnoticeContent() {
		return noticeContent;
	}
	public void setnoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	
	public String getnoticeFile() {
		return noticeFile;
	}
	public void setnoticeFile(String noticeFile) {
		this.noticeFile = noticeFile;
	}


	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
	public String getnoticeDate_Str() {
		return simpleDate.format(noticeDate);
	}
	public void setnoticeDate_Str(String noticeDate) {
		try {
			this.noticeDate = simpleDate.parse(noticeDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
