package kosta.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.Cart;

public interface CartDAO {
	/**
	 * 회원별 장바구니 조회
	 *  구매자용 메뉴에서 사용
	 * */
	List<Cart> cartSelectByCustomerId(String customerId) throws SQLException;
	
	/**
	 * 장바구니 담기
	 * */
	//int insertCart(Cart cart) throws SQLException;
	

	/**
	 * 장바구니번호를 입력해서 장바구니 삭제하기 
	 * */
	//int deleteCart(int cartNo) throws SQLException;

	/**
	 * 장바구니 전체 조회하기
	 * */
	List<Cart> cartSelectAll() throws SQLException;

	/**
	 * 장바구니번호를 입력해서 장바구니 삭제하기 
	 * */
	int deleteCart(String customerId) throws SQLException;

	/**
	 * 장바구니 담기
	 * */
	int insertCart(String customerId, int liquorNo, int cartCount) throws SQLException;

}
