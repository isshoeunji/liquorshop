 package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dao.CustomerDAO;
import kosta.mvc.model.dao.CustomerDAOImpl;
import kosta.mvc.model.dao.OrderDAO;
import kosta.mvc.model.dao.OrderDAOImpl;
import kosta.mvc.model.dto.OrderDetail;
import kosta.mvc.model.dto.Orders;

public class OrderService {
	OrderDAO orderDao = new OrderDAOImpl();
	
	/**
	 * 주문 내역 전체 보기
	 * 		판매자용메뉴에서 사용함.
	 * */
	public List<Orders> orderSelectAll() throws SQLException{
		List<Orders> list = orderDao.orderSelectAll();
		 if(list==null || list.size()==0)throw new SQLException("주문내역이 없습니다.");
		 return list;
	}

	 /**
	  * 회원별 주문 내역보기
	  * */
	 public List<Orders> selectOrdersByUserId(String customerId)throws SQLException{
		 List<Orders> list = orderDao.orderSelectByCustomerId(customerId);
		 if(list==null || list.size()==0)throw new SQLException(customerId+"의 주문내역이 없습니다.");
		 return list;
	 }
	 /**
		 * 주문 수정
		 * 		판매자가 주문상태 수정할때 사용함.
		 * */
	public void updateOrder(int orderNo, String orderStatusMessage)throws SQLException{
		int result =  orderDao.updateOrder(orderNo, orderStatusMessage);
		if(result==0)throw new SQLException("주문 수정을 실패하였습니다.");
	}
	
	/**
	 * 주문 삭제
	 * 		판매자 메뉴에서 삭제하거나, 
	 * 		구매자가 주문취소에서 삭제
	 * 		(주문상태에따라 취소 가능 여부 체크!)
	 * */
	public void deleteOrder(int orderNo)throws SQLException{
		
		if(checkOrderStatus(orderNo)) {
			int result =  orderDao.deleteOrder(orderNo);
			if(result==0)throw new SQLException("주문 삭제에 실패하였습니다.");
		}else {
			throw new SQLException("현재 주문 상태에서는 주문 취소가 불가능합니다.");
		}
	}
		
   /**
    * 주문하기
    * */
	 public void insertOrders(Orders orders, int couponNo) throws SQLException{
		//쿠폰 넘버로 몇% 세일하는지 알아내기
		 int salePercent = orderDao.getSalePercentByCouponNo(couponNo);
		
		 //오더 객체게 몇% 세일 받는지 넣어주기
		orders.setDiscount(salePercent);
		
		int result =  orderDao.insertOrder(orders);
		if(result==0)throw new SQLException("주문하기가 실패하였습니다.");
	 }
	 
	 /**
	  * 주문 번호로 주문 정보 가져오기.
	  * */
	 public Orders selectOrderByOrderNo(int orderNo) throws SQLException{
		 Orders returnVal = orderDao.selectOrderByOrderNo(orderNo);
		 if(returnVal == null) throw new SQLException(orderNo + "번의 주문이 없습니다.");
		 return returnVal;
	 }
	 
	 /**
	  * 주문 번호로 주문 상태 체크하기.
	  * @return : true 면 결제확인중 즉, 주문 삭제 가능. 
	  * 		  false면 결제확인중이 아님. 즉, 주문 삭제 불가능.
	  * */
	 public boolean checkOrderStatus(int orderNo) throws SQLException{
		 boolean returnValue = false;
		 
		 String orderStatus = orderDao.getOrderStatusByOrderNo(orderNo);
		 
		 if(orderStatus.equals("결제확인중")) returnValue = true;
		 
		 return returnValue;
	 }
	 /**
	  * 회원 아이디로 장바구니를 주문상세 리스트로 변환하기
	  * */
	 public List<OrderDetail> convertCartIntoOrderDetailListByCustomerId(String customerId) throws SQLException, NotFoundException{
		 List<OrderDetail> orderDetailList = orderDao.convertCartIntoOrderDetailListByCustomerId(customerId);
		 if(orderDetailList == null) throw new NotFoundException("장바구니에 물건이 없습니다.");
		 return orderDetailList;
	 }
}
