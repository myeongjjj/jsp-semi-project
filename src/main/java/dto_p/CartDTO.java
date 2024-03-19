package dto_p;

public class CartDTO {
	String cartTitle, cartFile,option1,option2,userId,
		   receiver,postNum,orderAddress,message,phoneNum;
	int prodPrice, prodNum,prodCnt, no;
	boolean prodChk;
	
	public String getCartTitle() {
		return cartTitle;
	}
	public void setCartTitle(String cartTitle) {
		this.cartTitle = cartTitle;
	}
	public String getCartFile() {
		return cartFile;
	}
	public void setCartFile(String cartFile) {
		this.cartFile = cartFile;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getProdNum() {
		return prodNum;
	}
	public void setProdNum(int prodNum) {
		this.prodNum = prodNum;
	}
	public int getProdCnt() {
		return prodCnt;
	}
	public void setProdCnt(int prodCnt) {
		this.prodCnt = prodCnt;
	}
	public boolean isProdChk() {
		return prodChk;
	}
	public void setProdChk(boolean prodChk) {
		this.prodChk = prodChk;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getPostNum() {
		return postNum;
	}
	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
}
