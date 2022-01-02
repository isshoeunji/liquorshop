package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dto.OrderDetail;
import kosta.mvc.model.dto.Orders;

public interface OrderDAO {
	
	/**
	 * 주문 내역 전체 보기
	 * 		판매자용메뉴에서 사용함.
	 * */
	List<Orders> orderSelectAll()throws SQLException;
	
	/**
	 * 회원별 주문내역보기
	 * 		구매자 주문내역 보기랑 판매자 주문 검색에서 사용함
	 * */
	List<Orders> orderSelectByCustomerId(String customerId)throws SQLException;
	
	/**
	 * 주문 수정
	 * 		판매자가 주문상태 수정할때 사용함.
	 * */
	int updateOrder(int orderNo, String orderStatusMessage)throws SQLException;
	
	/**
	 * 주문 삭제
	 * 		판매자 메뉴에서 삭제하거나, 
	 * 		구매자가 주문취소에서 삭제
	 * 		(주문상태에따라 취소 가능 여부 체크!)
	 * */
	int deleteOrder(int orderNo)throws SQLException;
	
	 /**
	   * 주문하기
	   *  1) orders테이블에 insert
	   *  2) order_line테이블에 insert
	   *  3) 재고량(stock)감소 시키기(update)
	   * 	
	   * 	구매자 메뉴에서 사용함.
	   * 	막판에 쿠폰 체크하는거 잊지 말기! 
	   * 
	   * */
	int insertOrder(Orders order)throws SQLException;
	
	/**
	 * 주문상세 등록하기 
	 * */
	int[] orderDetailInsert(Connection con  , Orders order, int seq) throws SQLException;
	
	 /**
	  * 주문 번호로 주문 정보 가져오기.
	  * */
	 Orders selectOrderByOrderNo(int orderNo) throws SQLException;
	 
	 /**
		 * 오더 넘버로 고객 아이디 가져오기
		 * 
		 * */
	String getCustomerIdByOrderNo(int orderNo) throws SQLException;
	
	/**
	 * 주문번호로 주문상태 여부 가져오기
	 * */
	String getOrderStatusByOrderNo(int orderNo) throws SQLException;

	 /**
	  * 해당 회원 번호를 입력하고 장바구니를 오더객체로 만들기
	  * */
	List<OrderDetail> convertCartIntoOrderDetailListByCustomerId(String customer_id) throws SQLException, NotFoundException;
	
	/**
	 * 쿠폰 넘버로 할인율 구해오기.
	 * */
	int getSalePercentByCouponNo(int couponNo) throws SQLException;
	 
}
