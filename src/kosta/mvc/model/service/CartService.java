package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dao.CartDAO;
import kosta.mvc.model.dao.CartDAOImpl;
import kosta.mvc.model.dto.Cart;

public class CartService {
	private CartDAO cartDAO = new CartDAOImpl();
	/**
	 * 회원 아이디별로 장바구니 조회하기
	 * */
	public List<Cart> cartSelectByCustomerId(String customerId) throws SQLException {
		List<Cart> cartList = cartDAO.cartSelectByCustomerId(customerId);
		if(cartList == null || cartList.isEmpty()) {
			throw new SQLException(customerId + "의 장바구니가 비어있습니다.");
		}
		
		return cartList;
	}
	
	/**
	 * 장바구니 담기
	 * */
	public void insertCart(String customerId, int liquorNo, int cartCount) throws SQLException {
		int result = cartDAO.insertCart(customerId, liquorNo, cartCount);
		if(result == 0) throw new SQLException("등록되지 않았습니다.");

	}
	
	/**
	 * 장바구니 삭제하기
	 * */
	public void deleteCart(String customerId) throws SQLException {
		int result = cartDAO.deleteCart(customerId);
		if(result == 0) throw new SQLException("삭제되지 않았습니다.");

	}
}
