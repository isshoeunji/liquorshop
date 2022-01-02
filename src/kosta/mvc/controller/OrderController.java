package kosta.mvc.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dao.CustomerDAO;
import kosta.mvc.model.dao.CustomerDAOImpl;
import kosta.mvc.model.dao.OrderDAO;
import kosta.mvc.model.dao.OrderDAOImpl;
import kosta.mvc.model.dto.Customer;
import kosta.mvc.model.dto.OrderDetail;
import kosta.mvc.model.dto.Orders;
import kosta.mvc.model.service.OrderService;
import kosta.mvc.view.EndView;
import kosta.mvc.view.FailView;
import kosta.mvc.view.OrderEndView;

public class OrderController {
    private static OrderService orderService = new OrderService();
    private static CustomerDAO customerDao = new CustomerDAOImpl();
    private static OrderDAO orderDao = new OrderDAOImpl();
	
    /**
	 * 주문 내역 전체 보기
	 * 		판매자용메뉴에서 사용함.
	 * */
	public static void orderSelectAll(){
		try {
			 List<Orders> orderList = orderService.orderSelectAll();
			 OrderEndView.printAllOrders(orderList);
		} catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	
	}
	
    /**
	 * 회원별 주문 내역 보기
	 * */
	public static void selectOrdersByUserId(String customerId) {
		try {
			 List<Orders> orderList = orderService.selectOrdersByUserId(customerId);
			 String customerName = customerDao.findCustomerName(customerId);
			 OrderEndView.printOrders(orderList, customerName);
		}catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			
		}
	}
	
	/**
	 * 주문 수정
	 * 		판매자가 주문상태 수정할때 사용함.
	 * */
	public static void updateOrder(int orderNo, String orderStatusMessage) {
		try {
			orderService.updateOrder(orderNo, orderStatusMessage);
		} catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
    
	/**
	 * 주문 삭제
	 * 		판매자 메뉴에서 삭제하거나, 
	 * 		구매자가 주문취소에서 삭제
	 * 		(주문상태에따라 취소 가능 여부 체크!)
	 * */
	public static void deleteOrder(int orderNo){
		try {
			orderService.deleteOrder(orderNo);
			EndView.meesegePrint("주문번호" + orderNo + "번이 취소되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
		}
	}
	
    /**
	 * 주문하기
	 * */
	public static void insertOrders(Orders order, int couponNo) {
		try {
		orderService.insertOrders(order, couponNo);
		EndView.meesegePrint("주문이 완료되었습니다!");
		}catch (Exception e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());			
		}
	}
	
	/**
	  * 주문 번호로 주문 정보 가져오기.
	  * */
	 public static void selectOrderByOrderNo(int orderNo){
		 try {
			 
			 List<Orders> orderList = new ArrayList<Orders>();
			 Orders order = orderService.selectOrderByOrderNo(orderNo);
			 orderList.add(order);
			 String customerId = orderDao.getCustomerIdByOrderNo(orderNo);
			 String customerName = customerDao.findCustomerName(customerId);

			 OrderEndView.printOrders(orderList, customerName);
		} catch (SQLException e) {
			//e.printStackTrace();
			FailView.errorMessage(e.getMessage());		
		}
	 }
	 
	 /**
	  * 장바구니로 주문하기.
	  * */
	 public static void OrderCart(String customerId, String addr, int couponNo){
		 try {
			 //일단 오더 객체를 하나 만듬.
			 Orders order = new Orders(0, customerId, null, addr, null, 0, 0);
			 //주문번호 : 0(sql에서 시퀀스로 들어갈꺼임)
			 //회원번호 : 입력받은 번호 (세션객체에서 가져와야됨)
			 //주문일자 : null (sql에서 sysdate으로 들어갈꺼임)
			 //배송주소 : 입력받은 주소 (스캐너로 입력받아서 넣어줘야함)
			 //주문상태 : null (sql에서 default값으로 들어갈꺼임)
			 //최종가격 : 0 (DAO에서 계산해줄 예정)
			 
			 //서비스에서 오더디테일 리스트 가져오기
			 List<OrderDetail> list = orderService.convertCartIntoOrderDetailListByCustomerId(customerId);
			 
			 //서비스에서 가져온 오더디테일을 하나씩 꺼내서... 
			 for(OrderDetail detail : list) {
				 //위에서 새로 만든 오더 객체의 오더디테일 리스트에 하나씩 넣어줌.
				 order.getOrderDetailList().add(detail);				 
			 }
			 //완성된 오더 객체를 서비스의 주문하기 메소드로 날림.	 
			 orderService.insertOrders(order, couponNo);
		 } catch (Exception e) {
			// e.printStackTrace();
			 FailView.errorMessage(e.getMessage());	
		 }
	 }
	
	
}




