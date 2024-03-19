package dto_p;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardDTO {
	private int perNum;//게시물 번호
	private String perTitle;//제목
	private String userId;//작성자
	private Date perDate;//작성일시
	private String perAnswer;//답변
	private String perContent;//내용
	private String boardCate;//카테고리
	private String perFile;//파일 업로드
	private String answer;//파일 업로드
	
	
	public int getPerNum() {
		return perNum;
	}
	public void setPerNum(int perNum) {
		this.perNum = perNum;
	}
	public String getPerTitle() {
		return perTitle;
	}
	public void setPerTitle(String perTitle) {
		this.perTitle = perTitle;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getPerDate() {
		return perDate;
	}
	public void setPerDate(Date perDate) {
		this.perDate = perDate;
	}
	public String getPerAnswer() {
		return perAnswer;
	}
	public void setPerAnswer(String perAnswer) {
		this.perAnswer = perAnswer;
	}
	public String getPerContent() {
		return perContent;
	}
	public void setPerContent(String perContent) {
		this.perContent = perContent;
	}
	public String getBoardCate() {
		return boardCate;
	}
	public void setBoardCate(String boardCate) {
		this.boardCate = boardCate;
	}
	
	public String getPerFile() {
		return perFile;
	}
	public void setPerFile(String perFile) {
		this.perFile = perFile;
	}


	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
	public String getDateTime() {
		return simpleDate.format(perDate);
	}
	public void setDateTime(String perDate) {
		try {
			this.perDate = simpleDate.parse(perDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
