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
	 * ȸ��id���� ��ٱ��� ��ȸ�ϱ�
	 * */
	@Override
	public List<Cart> cartSelectByCustomerId(String customerId) throws SQLException {
		Connection con = null; //�ε� ����
		PreparedStatement ps =null; //����
		ResultSet rs= null;  //select������ ����� �����ϴ� ��ü
		List<Cart> cartList = new ArrayList<Cart>(); //���ϰ�
		
		String sql = proFile.getProperty("cart.selectByCustomerId"); //�ܺ� Ű������ ������ �޾ƿ´�.
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			//cart.selectByCustomerId=select * from cart where customer_id = ?
			ps.setString(1, customerId); //������ ?���� ������ Ÿ�Կ� ���� ���� �����Ѵ�
			
			rs = ps.executeQuery(); //select���� ���� �� executeQuery() �޼��带 ����Ͽ� ResultSet������ �����Ѵ�.
			
			//rs.next()�� ���� �������� ������ �� ������ true�� ��ȯ�ϰ�, Ŀ���� ��ĭ ������. �������� ������ false ��ȯ
			while(rs.next()) { 
				Cart cartDto = new Cart(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				cartList.add(cartDto);
			}
			
		}finally {
			DBUtil.dbClose(con, ps, rs); //�ݱ�
		}
		
		return cartList;
	}

	
	/**
	 * ��ٱ��� ���
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
	 * ȸ��ID�� �Է��ؼ� ��ٱ��� �����ϱ� 
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
			//?��
			ps.setString(1, customerId);

			result = ps.executeUpdate();
		}finally {
			DBUtil.dbClose(con, ps);
		}
		
		return result;
	}

	/**
	 * ��ٱ��� ��ü ��ȸ�ϱ�
	 * */
	
	@Override
	public List<Cart> cartSelectAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs= null;
		List<Cart> cartList = new ArrayList<Cart>();
		String sql = proFile.getProperty("cart.select"); //�̰��� ��
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
