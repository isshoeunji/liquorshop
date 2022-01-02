package kosta.mvc.model.dto;

public class Cart {
	private int cartNo;
	private String customerId;
	private int liquorNo;
	private int cartCount;

	public Cart() {
		super();
		
	}


	public Cart(int cartNo, String customerId, int liquorNo, int cartCount) {
		super();
		this.cartNo = cartNo;
		this.customerId = customerId;
		this.liquorNo = liquorNo;
		this.cartCount = cartCount;
	}



	public int getCartNo() {
		return cartNo;
	}



	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}



	public String getCustomerId() {
		return customerId;
	}



	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}



	public int getLiquorNo() {
		return liquorNo;
	}



	public void setLiquorNo(int liquorNo) {
		this.liquorNo = liquorNo;
	}



	public int getCartCount() {
		return cartCount;
	}



	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cart [cartNo=");
		builder.append(cartNo);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", liquorNo=");
		builder.append(liquorNo);
		builder.append(", cartCount=");
		builder.append(cartCount);
		builder.append("]");
		return builder.toString();
	}

	

	
	
}
