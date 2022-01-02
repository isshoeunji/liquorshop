package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kosta.mvc.model.dto.Cart;
import util.DBUtil;

public class CartDAOImpl implements CartDAO {
	private Properties proFile = DBUtil.getProFile();
	
	/**
	 * 회원id별로 장바구니 조회하기
	 * */
	@Override
	public List<Cart> cartSelectByCustomerId(String customerId) throws SQLException {
		Connection con = null; //로드 연결
		PreparedStatement ps =null; //실행
		ResultSet rs= null;  //select쿼리의 결과를 저장하는 객체
		List<Cart> cartList = new ArrayList<Cart>(); //리턴값
		
		String sql = proFile.getProperty("cart.selectByCustomerId"); //외부 키값으로 쿼리를 받아온다.
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			//cart.selectByCustomerId=select * from cart where customer_id = ?
			ps.setString(1, customerId); //쿼리의 ?값의 개수와 타입에 맞춰 값을 설정한다
			
			rs = ps.executeQuery(); //select쿼리 실행 시 executeQuery() 메서드를 사용하여 ResultSet형으로 리턴한다.
			
			//rs.next()를 통해 다음행을 내려갈 수 있으면 true를 반환하고, 커서를 한칸 내린다. 다음행이 없으면 false 반환
			while(rs.next()) { 
				Cart cartDto = new Cart(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				cartList.add(cartDto);
			}
			
		}finally {
			DBUtil.dbClose(con, ps, rs); //닫기
		}
		
		return cartList;
	}

	
	/**
	 * 장바구니 담기
	 * */
	@Override
	public int insertCart(String customerId, int liquorNo, int cartCount) throws SQLException {
		Connection con = null;
		PreparedStatement ps =null;
		int result = 0;
		String sql = proFile.getProperty("cart.insertCart");
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, customerId);
			ps.setInt(2, liquorNo);
			ps.setInt(3, cartCount);
		
			result = ps.executeUpdate();
		}finally {
			DBUtil.dbClose(con, ps);
		}
		
		return result;
	}


	/**
	 * 회원ID를 입력해서 장바구니 삭제하기 
	 * */
	@Override
	public int deleteCart(String customerId) throws SQLException {
		Connection con = null;
		PreparedStatement ps =null;
		int result = 0;
		String sql = proFile.getProperty("cart.deleteCart");
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			//?값
			ps.setString(1, customerId);

			result = ps.executeUpdate();
		}finally {
			DBUtil.dbClose(con, ps);
		}
		
		return result;
	}

	/**
	 * 장바구니 전체 조회하기
	 * */
	
	@Override
	public List<Cart> cartSelectAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs= null;
		List<Cart> cartList = new ArrayList<Cart>();
		String sql = proFile.getProperty("cart.select"); //이것을 외
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Cart cartDto = new Cart(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				cartList.add(cartDto);
			}
		}finally {
			DBUtil.dbClose(con, ps, rs);
		}
		
		return cartList;
	}

	

}
