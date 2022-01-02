package kosta.mvc.model.dto;

import java.util.ArrayList;
import java.util.List;

public class Orders {
	private int orderNo;
	private String customerId;
	private String orderDate;
	private String orderAddr;
	private String orderStatus;
	private int finalPrice;
	private int discount;
	
	private List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(int orderNo, String customerId, String orderDate, String orderAddr, String orderStatus,
			int finalPrice, int discount) {
		super();
		this.orderNo = orderNo;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.orderAddr = orderAddr;
		this.orderStatus = orderStatus;
		this.finalPrice = finalPrice;
		this.discount = discount;
	}
	
	

	public Orders(int orderNo, String customerId, String orderDate, String orderAddr, String orderStatus,
			int finalPrice, int discount, List<OrderDetail> orderDetailList) {
		super();
		this.orderNo = orderNo;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.orderAddr = orderAddr;
		this.orderStatus = orderStatus;
		this.finalPrice = finalPrice;
		this.discount = discount;
		this.orderDetailList = orderDetailList;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderAddr() {
		return orderAddr;
	}

	public void setOrderAddr(String orderAddr) {
		this.orderAddr = orderAddr;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(int finalPrice) {
		this.finalPrice = finalPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Orders [orderNo=");
		builder.append(orderNo);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", orderDate=");
		builder.append(orderDate);
		builder.append(", orderAddr=");
		builder.append(orderAddr);
		builder.append(", orderStatus=");
		builder.append(orderStatus);
		builder.append(", finalPrice=");
		builder.append(finalPrice);
		builder.append(", discount=");
		builder.append(discount);
		builder.append(", orderDetailList=");
		builder.append(orderDetailList);
		builder.append("]");
		return builder.toString();
	}

	
	
}
