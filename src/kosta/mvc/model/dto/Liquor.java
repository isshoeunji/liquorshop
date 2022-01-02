package kosta.mvc.model.dto;

public class Liquor {
	private int liquorNo;
	private int liquorTableNo;
	private String liquorName;
	private int liquorPrice;
	private int stock;
	private String addDate;
	
	public Liquor() {
		super();
	}
	
	public Liquor(int liquorNo, int stock) {
		this.liquorNo = liquorNo;
		this.stock = stock;
	}
	
	public Liquor(int liquorNo, String liquorName, int liquorPrice) {
		this.liquorNo = liquorNo;
		this.liquorName = liquorName;
		this.liquorPrice = liquorPrice;
	}
	
	public Liquor(int liquorTableNo, String liquorName, int liquorPrice, String addDate) {
		this.liquorTableNo = liquorTableNo;
		this.liquorName = liquorName;
		this.liquorPrice = liquorPrice;
		this.addDate = addDate;
	}
	
	public Liquor(int liquorNo, String liquorName, int liquorPrice, int stock) {
		this.liquorNo = liquorNo;
		this.liquorName = liquorName;
		this.liquorPrice = liquorPrice;
		this.stock = stock;
	}
	
	
	public Liquor(int liquorNo, int liquorTableNo, String liquorName, int liquorPrice, int stock, String addDate) {
		this.liquorNo = liquorNo;
		this.liquorTableNo = liquorTableNo;
		this.liquorName = liquorName;
		this.liquorPrice = liquorPrice;
		this.stock = stock;
		this.addDate = addDate;
	}


	
	public int getLiquorNo() {
		return liquorNo;
	}
	public void setLiquorNo(int liquorNo) {
		this.liquorNo = liquorNo;
	}
	public int getLiquorTableNo() {
		return liquorTableNo;
	}
	public void setLiquorTableNo(int liquorTableNo) {
		this.liquorTableNo = liquorTableNo;
	}
	public String getLiquorName() {
		return liquorName;
	}
	public void setLiquorName(String liquorName) {
		this.liquorName = liquorName;
	}
	public int getLiquorPrice() {
		return liquorPrice;
	}
	public void setLiquorPrice(int liquorPrice) {
		this.liquorPrice = liquorPrice;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("*상품 번호: ");
		builder.append(liquorNo);
		builder.append("\t종류 번호: ");
		builder.append(liquorTableNo);
		builder.append("\t이름: ");
		builder.append(liquorName);
		builder.append("\t가격: ");
		builder.append(liquorPrice);
		builder.append("\t재고: ");
		builder.append(stock);
		builder.append("\t등록일: ");
		builder.append(addDate);
		return builder.toString();
	}
	
	
}
