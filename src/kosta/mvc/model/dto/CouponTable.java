package kosta.mvc.model.dto;

public class CouponTable {
	private int couponTableNo;
	private int couponNo;
	
	public CouponTable() {}
	public CouponTable(int couponTableNo, int couponNo) {
		super();
		this.couponTableNo = couponTableNo;
		this.couponNo = couponNo;
	}
	
	public int getCouponTableNo() {
		return couponTableNo;
	}
	public void setCouponTableNo(int couponTableNo) {
		this.couponTableNo = couponTableNo;
	}
	public int getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(int couponNo) {
		this.couponNo = couponNo;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CouponTable [couponTableNo=");
		builder.append(couponTableNo);
		builder.append(", couponNo=");
		builder.append(couponNo);
		builder.append("]");
		return builder.toString();
	}
}
