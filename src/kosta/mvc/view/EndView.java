package kosta.mvc.view;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dao.CustomerDAO;
import kosta.mvc.model.dao.CustomerDAOImpl;
import kosta.mvc.model.dto.Cart;
import kosta.mvc.model.dto.Coupon;
import kosta.mvc.model.dto.OrderDetail;
import kosta.mvc.model.dto.Orders;
import kosta.mvc.model.dto.Review;

public class EndView {
  /**
   * 상품 전체 출력
   * */
	/*public static void printGoodsList(List<Goods> list) {
		System.out.println("-----상품 "+ list.size() +"개 -------------");
		for(Goods goods : list) {
			System.out.println(goods);
		}
		
		System.out.println();
	}*/
	
	
	
	public static void printMessage(String message) {
		System.out.println(message);
	}

	public static void printCouponList(List<Coupon> couponList) {
		for(Coupon coupon : couponList) {
			System.out.println(coupon);
		}
	}

	public static void printCoupon(Coupon coupon) {
		System.out.println(coupon);
	}
	
	//Review, Cart
	/**
	 * 전체 리뷰 조회
	 * */
	public static void printReivew(List<Review> reviewList){
		System.out.println("--총 리뷰 수 : " + reviewList.size()+ "개 ----");
		for(Review r : reviewList) {
			System.out.println(r);
		}
		System.out.println();
	}
	
	/**
	 * 고객아이디 해당 리뷰 조회
	 * */
	public static void printReviewByCustomerId(List<Review> reviewList){
		for(Review r : reviewList) {
			System.out.println(r);
		}
		System.out.println();
		
	}
	
	/**
	 * 양주종류별 리뷰 검색
	 * */
	public static void printReviewByLiquorNo(List<Review> reviewList){
		for(Review r : reviewList) {
			System.out.println(r);
		}
		System.out.println();
		
	}
	
	/**
	 * 성공했을 때 메세지 출력
	 * */
	public static void meesegePrint(String message) {
		System.out.println(message);
	}
	
	/**
	 * 고객아이디별 장바구니 조회
	 * */
	public static void printCartByCustomerId(List<Cart> cartList) {
		for(Cart r : cartList) {
			System.out.println(r);
		}
		System.out.println();
	}//Review cart 끝
	
	/**
	 * 장바구니 보기
	 * */
	/*public static void printViewCart(String id , Map<Goods,Integer> cart) {
		System.out.println("장바구니내용....");
		
		for(Goods goods: cart.keySet()) { //모든 Key를 꺼낸다 
			String goodsId = goods.getGoodsId();//상품번호
			String name = goods.getGoodsName();//상품이름
			int price = goods.getGoodsPrice();//상품가격
			
			int quantity = cart.get(goods);//
			System.out.println(goodsId + " : "+name+" : " + price + " \t " + quantity);
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1.주문하기  |  9.나가기");
		switch(Integer.parseInt(sc.nextLine())) {
		case 1:
			
			 System.out.print("배송주소 : ");
			 String address = sc.nextLine();
			
			 Orders orders = new Orders(0, null, id, address, 0);
			 
			 List<OrderLine> orderLineList = orders.getOrderLineList();
			 
			 for(Goods goodsKey : cart.keySet()) {
				 int qty = cart.get(goodsKey);
				 OrderLine orderLine = new OrderLine(0, 0, goodsKey.getGoodsId() , 0, qty, 0);
				 orderLineList.add(orderLine);
			 }
			 
			 System.out.println("orderLineList 개수 : " + orderLineList.size());
			 OrderController.insertOrders(orders);
			 
			 //장바구니비우기
			 SessionSet ss = SessionSet.getInstance();
			 Session session = ss.get(id);
			 session.removeAttribute("cart");
			break;
			
		case 9:
			break;
		}
		
		//System.out.println(id);
	
	}*/
 
	/**
	 * 회원별 주문 상세보기
	 * */
//	public static void printOrders(List<Orders> orderList, String customerName) {
//	   for(Orders order : orderList) {
//		   
//		   System.out.println(order.getOrderNo()+ " | " + order.getOrderDate() +" | " + order.getFinalPrice() +" | " + order.getOrderAddr() +" | " + customerName);
//		   for(OrderDetail orderDetail : order.getOrderDetailList()) {
//			   System.out.println("  ▶ "+orderDetail);
//		   }
//		   System.out.println();
//	   }
//		
//	}
	/**
	 * 모든 주문 상세보기
	 * */
//	public static void printAllOrders(List<Orders> orderList) {
//	   for(Orders order : orderList) {
//		   
//		   System.out.println(order.getOrderNo()+ " | " + order.getOrderDate() +" | " + order.getFinalPrice() +" | " + order.getOrderAddr() +" | " + order.getCustomerId());
//		   for(OrderDetail orderDetail : order.getOrderDetailList()) {
//			   System.out.println("  ▶ "+orderDetail);
//		   }
//		   System.out.println();
//	   }
//		
//	}
}












