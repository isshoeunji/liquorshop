package kosta.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kosta.mvc.model.dto.Liquor;
import kosta.mvc.model.dto.LiquorTable;
import kosta.mvc.model.dto.Orders;
import util.DBUtil;

public class LiquorDAOImpl implements LiquorDAO {
	
	private Properties profile = DBUtil.getProFile();
	
	
	///////////테스트/////////
	public static void main(String[] args) {
		LiquorDAOImpl dao = new LiquorDAOImpl();
		try {
			/*
			List<Liquor> list = dao.liquorsSelectByLiquorPrice(16000);
			for(Liquor l : list) {
				System.out.println(l);
			}
			System.out.println("-------------------------------");
			List<Liquor> list2 = dao.liquorsSelectByLiquorType(1);
			for(Liquor l : list2) {
				System.out.println(l);
			}
			System.out.println("-------------------------------");
			Liquor liquor1 = dao.liquorSelectByLiquorNo(1);
			System.out.println(liquor1);
			System.out.println("-------------------------------");
			//insert into liquor values(liquor_no, ?, ?, ?, default, ?)
			//Liquor liquor2 = dao.insertLiquor(Liquor liquorDTO)
			Liquor liquor2 = new Liquor(1, "새 양주이름", 13000, "21-08-23");
			System.out.println(dao.insertLiquor(liquor2));
			System.out.println("-------------------------------");
			Liquor liquor3 = new Liquor(1, "바꾼 양주이름", 200000);
			System.out.println(dao.updateLiquor(liquor3));
			
			System.out.println("-------------------------------");
			System.out.println(dao.deleteLiquor(1));
			
			
			System.out.println(dao.liquorSelectByLiquorName("몬스터"));
			System.out.println("-------------------------------");
			
			
			System.out.println(dao.selectLiquorTable());
			*/
			/*System.out.println(dao.liquorSelectByLiquorName("몬스터"));
			Liquor liquor4 = new Liquor(41, 20);
			System.out.println(dao.updateStock(liquor4));*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	///////////테스트////////////
	
	@Override
	public List<Liquor> liquorsSelectByLiquorPrice(int price) throws SQLException {
		Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  List<Liquor> list = new ArrayList<>();
		 try {
		   con = DBUtil.getConnection();
		   ps= con.prepareStatement(profile.getProperty("liquor.liquorsSelectByLiquorPrice"));
		   ps.setInt(1, price);
		   ps.setInt(2, price);
	       rs = ps.executeQuery(); 
	        
	        while(rs.next()) {
	        	Liquor liquors  = new Liquor(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
	        	list.add(liquors);
	        }
    }finally {
    	DBUtil.dbClose(con, ps, rs);
    }
		return list;
	}

	@Override
	public List<LiquorTable> selectLiquorTable() throws SQLException {
		Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  List<LiquorTable> list = new ArrayList<>();
		 try {
		   con = DBUtil.getConnection();
		   ps= con.prepareStatement(profile.getProperty("liqourTable.selectLiquorTable"));
	       rs = ps.executeQuery(); 
	       while(rs.next()) {
	        	LiquorTable liquorTable = new LiquorTable(rs.getInt(1), rs.getString(2));
	        	list.add(liquorTable);
	        }
	       
		 }finally {
			 DBUtil.dbClose(con, ps, rs);
		 }
		return list;
	}
	
	@Override
	public List<Liquor> liquorsSelectByLiquorType(int liquorTableNo) throws SQLException {
		Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  List<Liquor> list = new ArrayList<>();
		 try {
		   con = DBUtil.getConnection();
		   ps= con.prepareStatement(profile.getProperty("liqour.liquorsSelectByLiquorType"));
		   ps.setInt(1, liquorTableNo);
	       rs = ps.executeQuery(); 
	        
	        while(rs.next()) {
	        	Liquor liquors = new Liquor(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
	        	list.add(liquors);
	        }
		 }finally {
			 DBUtil.dbClose(con, ps, rs);
		 }
		return list;
	}

	@Override
	public Liquor liquorSelectByLiquorNo(int liquorNo) throws SQLException {
		Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  Liquor liquor = null;
		  
		 try {
		   con = DBUtil.getConnection();
		   ps= con.prepareStatement(profile.getProperty("liquor.liquorSelectByLiquorNo"));
		   ps.setInt(1, liquorNo); 
	       rs = ps.executeQuery();
	       
	       if(rs.next()) {
	        	liquor = new Liquor(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
	        }
	       
		 }finally {
			 DBUtil.dbClose(con, ps, rs);
		 }
		return liquor;
	}
	
	@Override
	public Liquor liquorSelectByLiquorName(String name) throws SQLException {
		Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  Liquor liquor = null;
		 
		 try {
		   con = DBUtil.getConnection();
		   ps= con.prepareStatement(profile.getProperty("liquor.liquorSelectByLiquorName"));
		   ps.setString(1, "%"+name+"%");
	       rs = ps.executeQuery();
	       
	       if(rs.next()) {
	        	liquor = new Liquor(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
	        }
	       
		 }finally {
			 DBUtil.dbClose(con, ps, rs);
		 }
		return liquor;
	}
	
	@Override
	public int insertLiquor(Liquor liquorDTO) throws SQLException{
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(profile.getProperty("liquor.insertLiquor"));
			
			//LIQUOR_TABLE_NO
			ps.setInt(1, liquorDTO.getLiquorTableNo());
			//LIQUOR_NAME
			ps.setString(2, liquorDTO.getLiquorName());
			//LIQUOR_PRICE
			ps.setInt(3, liquorDTO.getLiquorPrice());
			//ADD_DATE
			ps.setString(4, liquorDTO.getAddDate());
			
			result = ps.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}


	@Override
	public int updateLiquor(Liquor liquorDTO) throws SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = profile.getProperty("liquor.updateLiquor");
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);

			//liquor_name = ?, liquor_price = ?, stock = ? where liquor_no = ? 
			ps.setString(1, liquorDTO.getLiquorName());
			ps.setInt(2, liquorDTO.getLiquorPrice());
			ps.setInt(3, liquorDTO.getStock());
			ps.setInt(4, liquorDTO.getLiquorNo());
			
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

	
	@Override
	public int deleteLiquor(int liquorNo) throws SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = profile.getProperty("liquor.deleteLiquor");
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			//delete from liquor where liquor_no = ?
			ps.setInt(1, liquorNo);
			
			result = ps.executeUpdate();
				
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public int updateStock(Liquor liquorDTO) throws SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = profile.getProperty("liquor.newStock");
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			//update liquor set stock= ? where liquor_no=? 
			ps.setInt(1, liquorDTO.getStock());
			ps.setInt(2, liquorDTO.getLiquorNo());
			
			result = ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

}
