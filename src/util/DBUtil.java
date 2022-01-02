package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * DB연동을 위한 로드, 연결, 닫기
 */
public class DBUtil {
	public static void main(String[] args) {
	    try {
		    Connection con = getConnection();
		    System.out.println("연결 완료 = " + con);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
    }
	
	private static Properties proFile = new Properties();
	
	public static Properties getProFile() {
		return proFile;
	}
	
	/**
	 * 로드
	 */
	static {
		try {
			proFile.load(new FileInputStream("resources/dbInfo.properties"));
			//proFile.load(new FileInputStream("resources/sql.properties"));
			proFile.load(new FileInputStream("resources/cart_sql.properties"));
			proFile.load(new FileInputStream("resources/coupon_sql.properties"));
			proFile.load(new FileInputStream("resources/customer_sql.properties"));
			proFile.load(new FileInputStream("resources/liquor_sql.properties"));
			proFile.load(new FileInputStream("resources/order_sql.properties"));
			proFile.load(new FileInputStream("resources/review_sql.properties"));
		
			Class.forName(proFile.getProperty("driverName")); // 오라클 드라이버를 찾는다.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 연결
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(proFile.getProperty("url"), proFile.getProperty("userName"), proFile.getProperty("userPass"));
	}

	/**
	 * 닫기(select인 경우)
	 */
	public static void dbClose(Connection con, Statement st, ResultSet rs) {
		try {
			if (rs != null)	rs.close();
			dbClose(con, st);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 닫기(DML, DDL인 경우)
	 */
	public static void dbClose(Connection con, Statement st) {

		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
