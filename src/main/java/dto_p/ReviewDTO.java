package dto_p;

public class ReviewDTO {
	int reviewNum,reviewStar,prodNum;
	String reviewTitle, reviewContent,userId;
	boolean decide;
	public int getReviewNum() {
		return reviewNum;
	}
	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}
	public int getReviewStar() {
		return reviewStar;
	}
	

	public void setReviewStar(int reviewStar) {
		this.reviewStar = reviewStar;
	}
	public int getProdNum() {
		return prodNum;
	}
	public void setProdNum(int prodNum) {
		this.prodNum = prodNum;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean isDecide() {
		return decide;
	}
	public void setDecide(boolean decide) {
		this.decide = decide;
	}
	
	
}
