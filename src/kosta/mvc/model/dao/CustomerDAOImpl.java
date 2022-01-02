package kosta.mvc.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kosta.mvc.model.dto.Customer;

import util.DBUtil;

public class CustomerDAOImpl implements CustomerDAO {
	private Properties proFile = DBUtil.getProFile();

/////////////////////TEST////////////////////////
/*public static void main(String[] args) {
	CustomerDAOImpl cd = new CustomerDAOImpl();
	Customer c = null;

	try {
	
		c = new Customer("m", "tiger","마스크","00-03-04","masklover@naver.com","경기도 판교","010-3333-3333"); 
		System.out.println(cd.insertCustomer(c));
	
		//customerLogin(String customerId, String customerpwd)
		c = cd.customerLogin("hong", "111");
		System.out.println(c);
		
		//updateCustomerEmail(String customerId, String email)
		System.out.println(cd.updateCustomerEmail("JANG", "JANG@naver.com"));
		
		//updateCustomerPwd(String customerId, String pwd)
		System.out.println(cd.updateCustomerPwd("YOO", "1234"));
	
		//updateCustomerAddr(String customerId, String addr)
		System.out.println(cd.updateCustomerAddr("JANG", "서울시 서초구"));
		
		//deleteCustomer(String customerId)
		System.out.pritnln(cd.deleteCustomer());
		
		System.out.println(cd.findCustomerId("AAA111@naver.com"));
		
		System.out.println(cd.findCustomerPwd("CHOI1", "AAA111@naver.com"));
		
		System.out.println(cd.findCustomerName("CHOI1"));
		

		List<Customer> list = cd.selectCustomerListAll();
			for(Customer ct : list) 
			System.out.print(ct);
	
	
		}catch (Exception e) {
		e.printStackTrace();
	}
}*/
/////////////////////TEST////////////////////////


	@Override
	public int insertCustomer(Customer customer) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = proFile.getProperty("customer.insertCustomer");
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			//insert into customer(customer_id, pwd, customer_name, birth, email, addr, contact, sign_date ) 
			//values( ?, ?, ?, ?, ?, ?, ?, sysdate)
			ps.setString(1, customer.getCustomerId());
			ps.setString(2, customer.getPwd());
			ps.setString(3, customer.getCustomerName());
			ps.setString(4, customer.getBirth());
			ps.setString(5, customer.getEmail());
			ps.setString(6, customer.getAddr());
			ps.setString(7, customer.getContact());
			
			
			result = ps.executeUpdate();
			
		} finally {
			DBUtil.dbClose(con, ps);
		}
		
		return result;
	}
////////////////////////////////////////////////
	// 아이디 중복 체크 
	@Override
	public String customerIdDuplicationCheck(String customerId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String Id = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(proFile.getProperty("customer.customerIdDuplicationCheck")) ;
			
			ps.setString(1, customerId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Id = rs.getString(1);
			}
			
		}finally {
			DBUtil.dbClose(con, ps ,rs);
		}
		return Id;
	}

	
/////////////////////////////////////////////////
	//로그인하기
	
	@Override
	public Customer customerLogin(String customerId, String customerpwd) throws SQLException {
		
		  Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  Customer customer=null;
		 try {
		   con = DBUtil.getConnection();
		   //customer.sellerCheck = select seller from customer where customer_id = ?
		   ps = con.prepareStatement(proFile.getProperty("customer.customerLogin"));
		   ps.setString(1, customerId);
		   ps.setString(2, customerpwd);
		   
	        rs = ps.executeQuery(); 
	        
	        if(rs.next()) {
	        	customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
	        }
      }finally {
      	DBUtil.dbClose(con, ps, rs);
      }
		return customer;
	}


	//email로  id찾기
	
	
	@Override
	public String findCustomerId(String email) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String id = null;
		
		try {
			con = DBUtil.getConnection();
			//SELECT CUSTOMER_ID FROM CUSTOMER WHERE EMAIL = ?
			ps = con.prepareStatement(proFile.getProperty("customer.findCustomerId")) ;
			
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				id = rs.getString(1);
			}
			
		}finally {
			DBUtil.dbClose(con, ps ,rs);
		}
		return id;
	}
		
	
// pwd 찾기  -아이디입력 + 이메일 
	@Override
	public String findCustomerPwd(String customerId, String email) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String pwd = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(proFile.getProperty("customer.findCustomerPwd")) ;
			
			//SELECT PWD FROM CUSTOMER WHERE EMAIL = ? AND CUSTOMER_ID = ?
			ps.setString(1, email);
			ps.setString(2, customerId);

			rs = ps.executeQuery();
			
			if(rs.next()) {
				pwd = rs.getString(1);
			}
			
		}finally {
			DBUtil.dbClose(con, ps ,rs);
		}
		
		return pwd;
	}
	
	@Override
	public String findCustomerName(String customerId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(proFile.getProperty("customer.findCustomerName")) ;
			
			ps.setString(1, customerId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				name = rs.getString(1);
			}
			
		}finally {
			DBUtil.dbClose(con, ps ,rs);
		}
		
		return name;
	}
/////////////////////////////////////////////////////////////////
	//회원정보수정 (이메일 수정)
	
	@Override
	public int updateCustomerEmail(String customerId, String email) throws SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("customer.updateCustomerEmail");
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql) ;
			
			//update customer set email = ? where customer_id = ?
			ps.setString(1, email);
			ps.setString(2, customerId);

			result = ps.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.dbClose(con, ps);
		}
		return result;
	}
	
//////////////////////////////////////////////////////////
// 회원정보수정 (비밀번호)
	
	@Override
	public int updateCustomerPwd(String customerId, String pwd) throws SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("customer.updateCustomerPwd");
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql) ;
			
			//update customer set pwd = ? where customer_id = ? 
			ps.setString(1, pwd);
			ps.setString(2, customerId);

			result = ps.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

///////////////////////////////////////////////////
//	 회원정보수정 (주소변경)	

	@Override
	public int updateCustomerAddr(String customerId, String addr) throws SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("customer.updateCustomerAddr");
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql) ;
			
			//update customer set addr = ? where customer_id= ?
			ps.setString(1, addr);
			ps.setString(2, customerId);
			
			result = ps.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

	
//////////////////////////////////////////////
// 회원탈퇴	
	
	@Override
	public int deleteCustomer(String customerId) throws SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("customer.deleteCustomer");
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql) ;
			
			ps.setString(1, customerId);
			result = ps.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.dbClose(con, ps);
		}
		return result;
	}	
	
	/**
	 * 회원 정보 확인
	 * (아이디를 넣으면 -> Customer 객체(DTO)를 돌려주는 메소드!)
	 * */
	public Customer selectCustomerByCustomerId(String customerId) throws SQLException{
		//일단 리턴할 커스터머DTO 베리어블 하나 만들고...
		Customer customer = null;
		
		//커넥션, 프리페어스테이트먼트(쿼리 들어갈거), 리절트 셋(결과값 나올거) 준비해둠.
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//프리페어 스테이트먼트에 실제로 들어갈 쿼리문 하나 준비해둠.
		String sql = "SELECT CUSTOMER_ID, PWD, CUSTOMER_NAME, BIRTH, EMAIL, ADDR, CONTACT, SIGN_DATE, SELLER FROM CUSTOMER WHERE CUSTOMER_ID=?";
		
		try {
			//con에 DBUtil에 있는 겟 커넥션 메소드로 커넥션 구축하기
			con = DBUtil.getConnection();
			//con에다가 프리페어 스테이트먼트 메소드 호출해서 위에서 만든 sql 즉, 쿼리 넣어줌.
			//그리고 이걸 프리페어 스테이트먼트 객체에 다시 넣어줌.
			ps = con.prepareStatement(sql);
			//쿼리문 마지막에 물음표(?)하나 있는 곳에다가 메소드에서 받아온 customerId를 넣어줌.
			ps.setString(1, customerId);
			//이렇게하면 쿼리문은...
			//SELECT CUSTOMER_ID...(생략) FROM CUSTOMER WHERE CUSTOMER_ID=회원번호가 됨.
			//즉, 회원번호가 일치하는 레코드의 아이디, 비번, 등등을 가져오라는 쿼리가 완성됨.
			
			//쿼리가 완성되었으니까 엑시큐트(실행) 해줌.
			//executeQuery메소드의 리턴타입이 리절트 셋이니까...
			//이걸 위에서 만들어둔 rs 변수에다가 넣어줌.
			rs = ps.executeQuery();
			
			if(rs.next()) { //만약에 rs.next()가 true라면...
							//즉, 결과값이 한줄이 있다면...
				//위에서 입력한 쿼리의 select한 컬럼들 즉,
				//CUSTOMER_ID, PWD, CUSTOMER_NAME, BIRTH, EMAIL, ADDR, CONTACT, SIGN_DATE, SELLER 
				// 1		   2    3              4      5      6     7        8          9 
				//요것들이 차례대로 들어올 예정임.
				String id = rs.getString(1); //첫번째=CUSTOMER_ID
				String pwd = rs.getString(2); //두번째=PWD
				String customerName = rs.getString(3); //세번째=CUSTOMER_NAME
				String birth = rs.getString(4); //네번째=BIRTH
				String email = rs.getString(5); //다섯번째=EMAIL
				String addr = rs.getString(6); //여섯번째=ADDR
				String contact = rs.getString(7); //일곱번째=CONTACT
				String signDate = rs.getString(8); //여덟번째=SIGN_DATE
				String seller = rs.getString(9); //아홉번째=SELLER
				
				//이제 받아온 값들을 아까 위에서 만든 리턴용 커스터머 객체(DTO)에다가 생성자로 넣어줌.
				customer= new Customer(customerId, pwd, customerName, birth, email, addr,contact, signDate, seller);
				//이렇게 완성된 커스터머 객체를 이제 리턴만 해주면 됨.
			}
			
			
		}finally {
			//메소드 끝내기 전에 만들어둔 con, ps, rs는 닫아줌.
			DBUtil.dbClose(con, ps, rs);
		}
		
		//위에서 처음에 customer를 만들때 기본적으로 null값을 줬으니까...
		//쿼리를 실행했을 때 결과값이 없으면 얘가 계속 null 일것이고...
		//결과값이 있었다면 생성자로 값이 들어가있는 Customer 객체(DTO)가 리턴될거임. 
		return customer;
	}
	
	/**
	 * 회원인지 판매자 확인  
	 * @return seller일 경우 "SELLER"(대문자 유의!) 값 리턴. 아닐 경우 null값 리턴  
	 **/
	public String sellerCheck (String customerId) throws SQLException{
		String seller = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = proFile.getProperty("customer.sellerCheck");
		
		try {
			con = DBUtil.getConnection();
			
			//select seller(default null값 줬던거) from customer where customer_id = ?
			ps = con.prepareStatement(sql) ;
			ps.setString(1, customerId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				seller = rs.getString(1);
			}			
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		
		return seller;
		//seller == null -> 회원
		//seller == SELLER -> 판매자 
	}

	
	/**
	 *  관리자 - 전체 회원 리스트 보기 
	 **/

	@Override
	public List<Customer> selectCustomerListAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null; 
		ResultSet rs = null;
		List<Customer>customerList = new ArrayList<Customer>();
		String sql = proFile.getProperty("customer.selectCustomerListAll" );
		//select customer_id, pwd, customer_name, birth, email, addr, contact, sign_date, seller from customer 

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
	
			while(rs.next()) {
				
				String customerId = rs.getString(1);
				String pwd = rs.getString(2);
				String customerName = rs.getString(3);
				String birth = rs.getString(4);
				String email = rs.getString(5);
				String addr = rs.getString(6);
				String contact = rs.getString(7);
				String signDate = rs.getString(8);
				String seller = rs.getString(9);
				
				Customer customerDto= new Customer(customerId, pwd, customerName, birth, email, addr, contact, signDate, seller);
								
				//Customer customerDto= new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				customerList.add(customerDto);
			}
		}catch(Exception e ) {
			e.printStackTrace();
		
		}finally {
			DBUtil.dbClose(con, ps,rs);
		}
		return customerList;
	}

	
	/**
	 * 관리자 - 회원강퇴 
	 **/
}
