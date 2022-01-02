package kosta.mvc.model.dto;

public class OrderDetail {
	private int orderDetailNo;
	private int liquorNo;
	private int orderNo;
	private int count;
	private int orderPrice;
	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetail(int orderDetailNo, int liquorNo, int orderNo, int count, int orderPrice) {
		super();
		this.orderDetailNo = orderDetailNo;
		this.liquorNo = liquorNo;
		this.orderNo = orderNo;
		this.count = count;
		this.orderPrice = orderPrice;
	}
	public int getOrderDetailNo() {
		return orderDetailNo;
	}
	public void setOrderDetailNo(int orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
	}
	public int getLiquorNo() {
		return liquorNo;
	}
	public void setLiquorNo(int liquorNo) {
		this.liquorNo = liquorNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		//builder.append("OrderDetail [orderDetailNo=");
		//builder.append(orderDetailNo);
		builder.append(" 양주 번호: ");
		builder.append(liquorNo);
		//builder.append(", orderNo=");
		//builder.append(orderNo);
		builder.append(" | 개수: ");
		builder.append(count);
		builder.append(" | 금액: ");
		builder.append(orderPrice);
		//builder.append("]");
		return builder.toString();
	}
	
}
