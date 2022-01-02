package kosta.mvc.view;

import java.util.ArrayList;
import java.util.List;

import kosta.mvc.model.dao.LiquorDAO;
import kosta.mvc.model.dao.LiquorDAOImpl;
import kosta.mvc.model.dto.Liquor;
import kosta.mvc.model.dto.OrderDetail;
import kosta.mvc.model.dto.Orders;

public class OrderEndView {
////////////////////////TEST/////////////////////////////////////////
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Orders orders = new Orders(0, "KIM", null, "서울시 송파구", null,0, 12);
//		 OrderDetail orderDetail1 = new OrderDetail(0, 21, 0, 1, 0);
//		 OrderDetail orderDetail2 = new OrderDetail(0, 22, 0, 1, 0);
//		 OrderDetail orderDetail3 = new OrderDetail(0, 23, 0, 1, 0);
//		 
//		 orders.getOrderDetailList().add(orderDetail1);
//		 orders.getOrderDetailList().add(orderDetail2);
//		 orders.getOrderDetailList().add(orderDetail3);
//		 
//		 List<Orders> orderList = new ArrayList<Orders>();
//		 orderList.add(orders);
//		 
//		 printOrders(orderList, "김아무개");
//	}
////////////////////////TEST/////////////////////////////////////////

	/**
	 * 회원별 주문 상세보기
	 * */
	public static void printOrders(List<Orders> orderList, String customerName) {
	   for(Orders order : orderList) {
		   
		   System.out.println("주문번호: " + order.getOrderNo()+ " | " + "주문일자: "+ order.getOrderDate() +" | " + "주문 총 금액: "+order.getFinalPrice() +" | "+"배송 주소: " + order.getOrderAddr() +" | " +"주문자명: "+ customerName + " | "+ "주문상태: " + order.getOrderStatus());
		   for(OrderDetail orderDetail : order.getOrderDetailList()) {
			   String liquorName = null;
			   try {
					LiquorDAO liquorDao = new LiquorDAOImpl();
					Liquor liquor = liquorDao.liquorSelectByLiquorNo(orderDetail.getLiquorNo());
					liquorName = liquor.getLiquorName();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			   System.out.println("  ▶ 주문한 양주: "+ liquorName + " | 개수: " + orderDetail.getCount() + " | 금액: " + orderDetail.getOrderPrice());

		   }
		   System.out.println();
	   }
		
	}
	/**
	 * 모든 주문 상세보기
	 * */
	public static void printAllOrders(List<Orders> orderList) {
	   for(Orders order : orderList) {
		   
		   System.out.println("주문번호: " + order.getOrderNo()+ " | " + "주문일자: "+ order.getOrderDate() +" | " + "주문 총 금액: "+order.getFinalPrice() +" | "+"배송 주소: " + order.getOrderAddr() +" | " +"주문자ID: "+ order.getCustomerId() + " | "+ "주문상태: " + order.getOrderStatus());
		   for(OrderDetail orderDetail : order.getOrderDetailList()) {
			   String liquorName = null;
			   try {
					LiquorDAO liquorDao = new LiquorDAOImpl();
					Liquor liquor = liquorDao.liquorSelectByLiquorNo(orderDetail.getLiquorNo());
					liquorName = liquor.getLiquorName();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			   System.out.println("  ▶ 주문한 양주: "+ liquorName + " | 개수: " + orderDetail.getCount() + " | 금액: " + orderDetail.getOrderPrice());
		   }
		   System.out.println();
	   }
		
	}
}
