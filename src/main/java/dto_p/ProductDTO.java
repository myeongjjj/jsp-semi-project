package dto_p;

public class ProductDTO {
	int prodNum, prodPrice;
	String prodTitle, prodCate, prodFile, prodInfo, option1, option2;
	
	
	public int getProdNum() {
		return prodNum;
	}
	public void setProdNum(int prodNum) {
		this.prodNum = prodNum;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getProdTitle() {
		return prodTitle;
	}
	public void setProdTitle(String prodTitle) {
		this.prodTitle = prodTitle;
	}
	public String getProdCate() {
		return prodCate;
	}
	public void setProdCate(String prodCate) {
		this.prodCate = prodCate;
	}
	public String getProdFile() {
		return prodFile;
	}
	public void setProdFile(String prodFile) {
		this.prodFile = prodFile;
	}
	public String getProdInfo() {
		return prodInfo;
	}
	
	public void setProdInfo(String prodInfo) {
		this.prodInfo = prodInfo;
	}
	
}
