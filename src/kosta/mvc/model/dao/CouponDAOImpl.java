package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kosta.mvc.model.dto.Coupon;
import util.DBUtil;

public class CouponDAOImpl implements CouponDAO {
	
	private Properties proFile = DBUtil.getProFile();
	
	/*public static void main(String[] args) {
		LocalDate now = LocalDate.now();
		System.out.println(now);
		CouponDAO dao = new CouponDAOImpl();
		//dao.insertCouponTable("JANG", 15);
		System.out.println("¼º°ø");
	}*/

	@Override
	public List<Coupon> couponSelectAll(String customerId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Coupon> couponList = new ArrayList<Coupon>();
		String sql = proFile.getProperty("coupon.selectByCustomerId");
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, customerId);
			rs = ps.executeQuery();
			while(rs.next()) {
				Coupon coupon = new Coupon(rs.getInt(2), rs.getInt(1), rs.getInt(3));
				couponList.add(coupon);
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return couponList;
	}

	@Override
	public Coupon couponSelectByCouponNo(String customerId, int couponNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Coupon coupon = null;
		String sql = proFile.getProperty("coupon.selectCoupon");
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, couponNo);
			ps.setString(2, customerId);
			rs = ps.executeQuery();
			while(rs.next()) {
				coupon = new Coupon(rs.getInt(1));
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return coupon;
	}
	
	@Override
	public int insertCouponTable(String customerId, int salePercent) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = proFile.getProperty("couponTable.insert");
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, customerId);
			
			result = ps.executeUpdate();
			
			int seq = getSeq(con);
			
			if(result!=0) {
				if (salePercent == 10) {
					LocalDate now = LocalDate.now();
					if (now.getDayOfWeek().getValue() == 3) {
						if (couponSelectBySysdate(con, customerId)) {
							return -10;
						}
					}else {
						return result;
					}
				}
			    int re = insertCoupon(con, salePercent, seq);
			}
		} finally {
			DBUtil.dbClose(con, ps);
		}
		
		return result;
	}

	@Override
	public int insertCoupon(Connection con, int salePercent, int seq) throws SQLException {
		PreparedStatement ps = null;
		int result = 0;
		String sql = proFile.getProperty("coupon.insert");
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, seq);
			ps.setInt(2, salePercent);
			
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(null, ps);
		}
		
		return result;
	}

	public boolean couponSelectBySysdate(Connection con, String customerId) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;
		String sql = proFile.getProperty("coupon.selectBySysdate");
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, customerId);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = true;
			}
		} finally {
			DBUtil.dbClose(null, ps, rs);
		}
		return result;
	}
	
	@Override
	public int useCoupon(int couponNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = proFile.getProperty("coupon.updateUsed");
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, couponNo);

			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}
	
	@Override
	public int getSeq(Connection con) throws SQLException{
		int seq = -1;
		PreparedStatement ps=null;
		ResultSet rs = null;
		String sql=proFile.getProperty("coupon.getseq");
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				seq = rs.getInt(1);
			}
			
		}finally {
	    	DBUtil.dbClose(null, ps , rs);
		}
		
		return seq;
	}
}
