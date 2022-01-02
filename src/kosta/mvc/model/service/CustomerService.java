package kosta.mvc.model.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dao.CouponDAO;
import kosta.mvc.model.dao.CouponDAOImpl;
import kosta.mvc.model.dao.CustomerDAO;
import kosta.mvc.model.dao.CustomerDAOImpl;
import kosta.mvc.model.dto.Customer;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;

public class CustomerService {
	CustomerDAO customerDao = new CustomerDAOImpl();
	CouponDAO coupon = new CouponDAOImpl();
	
	
	///////////////////테스트///////////////////
	/*public static void main(String args[]){
		CustomerService cs = new CustomerService();
		try {
			//System.out.println(cs.customerLogin("KIM", "4444"));
			//System.out.println(cs.findCustomerId("DDD444@naver.com"));
			//System.out.println(cs.findCustomerPwd("KIM", "DDD444@naver.com"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	///////////////////테스트///////////////////
	
	
  /**
   * 로그인
   * */
	public Customer customerLogin(String customerId, String customerPwd)throws NotFoundException , SQLException{
		Customer customer=customerDao.customerLogin(customerId, customerPwd);
		
		if(customer==null) {
			throw new NotFoundException("----------*****정보를 다시 확인해주세요.*****----------");
		}
		
		//판매자라면 SELLER 리턴 -> String seller에는 SELLER 값을 가짐 
		String seller = customer.getSeller();
		
		//????????????????????????????????????????????????????????????????????????이거 왜 넣은건지 물어보기
		/*if(!seller.equals("SELLER")) {
			coupon.insertCouponTable(customerId, 10);
		}*/
		
		//로그인 된 정보 저장하기
		Session session = new Session(customerId, seller);
		SessionSet sessionSet = SessionSet.getInstance();
		sessionSet.add(session);
		
		return customer;
	}
	

	/**
	 * 회원가입
	 * */
	public void register(Customer customer) throws SQLException{
		LocalDate now = LocalDate.now();
		String [] birth = customer.getBirth().split("/");
		int age = now.getYear() - Integer.parseInt(birth[0]);
		if(Integer.parseInt(birth[1]) > now.getMonthValue()) {
			age--;
		} else if(Integer.parseInt(birth[2]) >= now.getDayOfMonth()) {
			age--;
		}
		if(age >= 19) {
			if(customerIdDuplicationCheck(customer.getCustomerId()) ) throw new SQLException("----------*****아이디가 중복되었습니다.*****----------");
			
			int result =  customerDao.insertCustomer(customer);
		    if(result==0)throw new SQLException("----------*****회원가입에 실패하였습니다.*****----------");
		    //coupon.insertCouponTable(customer.getCustomerId(), 15);
		} else {
			throw new SQLException("----------*****애들은 가라*****----------");
		}
		
	}	
		
/**
 *  회원 가입 (	회원 아이디가 중복 못 넣게) 
 *  @return  아이디가 중복이면 true, 중복 아니면  false 
 **/
	
		public boolean customerIdDuplicationCheck(String customerId) throws SQLException {
			String id = customerDao.customerIdDuplicationCheck(customerId);
			if(id == customerId) {
				return true;
			}else {
				return false;
			}
		}
		


/**
 * 회원정보찾기 (아이디)
 **/
	public String findCustomerId(String email) throws NotFoundException, SQLException {
		String id =customerDao.findCustomerId(email);
		if(id == null) throw new NotFoundException("----------*****정보를 다시 확인해주세요.*****----------");
		return id;
	}
	
/**
 * 회원정보찾기 (비번)
 **/
	public String findCustomerPwd(String customerId, String email ) throws NotFoundException, SQLException {
		String pwd = customerDao.findCustomerPwd(customerId, email); 
		if(pwd == null) throw new NotFoundException("----------*****정보를 다시 확인해주세요.*****----------");
		return pwd;
	}

/**
 * 회원정보수정 (이메일) -update	
 **/
	public int updateCustomerEmail(String customerId, String email ) throws SQLException {
		int result = customerDao.updateCustomerEmail(customerId, email); 
		if(result == 0) throw new SQLException("----------*****정보를 다시 확인해주세요.*****----------");
		return result;
		
	}
	
/**	
 * 회원정보수정 (비번)-update
 **/
	public int updateCustomerPwd(String customerId, String pwd) throws SQLException {
		int result = customerDao.updateCustomerPwd(customerId, pwd); 
		if(result == 0) throw new SQLException("----------*****정보를 다시 확인해주세요.*****----------");
		return result;
		
	}
	
	
/**
 * 회원정보수정 (주소)-update
 **/
	
	public int updateCustomerAddr(String customerId, String Addr ) throws SQLException {
		int result = customerDao.updateCustomerAddr(customerId, Addr); 
		if(result == 0) throw new SQLException("----------*****정보를 다시 확인해주세요.*****----------");
		return result;
		
	}
/**
 * 회원 탈퇴 - delete
 **/
	public int deleteCustomer(String customerId) throws SQLException{
		int result = customerDao.deleteCustomer(customerId);
		if(result == 0) throw new SQLException("----------*****정보를 다시 확인해주세요*****----------");
		return result;
	}
	
/**
 * 회원 정보 확인
 * (아이디를 넣으면 -> Customer 객체(DTO)를 돌려주는 메소드!)
 * */
	public Customer selectCustomerByCustomerId(String customerId) throws SQLException{
	//서비스를 컨트롤러에서 호출해서 컨트롤러쪽에서 뷰 쪽에다가 뿌려줄 Customer를 줘야하니까
	//리턴해줄 Customer 베리어블 하나 만들어줌.
		//커스터머 DAO에서 만들어둔 selectCustomerByCustomerId 메소드를 여기에 넣어줌.
		Customer customer = customerDao.selectCustomerByCustomerId(customerId);
		
		if(customer == null) { //DAO에서 돌려준 커스터머 객체가 없다면...
			throw new SQLException("----------*****정보를 다시 확인해주세요*****----------");
		}
		
		return customer;
	}
	
//////////////////////////////////////////////////////////////////////	
	/**
	 * 회원인지 판매자 확인  
	 * @return seller일 경우 "SELLER"(대문자 유의!) 값 리턴. 아닐 경우 null값 리턴  
	 **/
	public String sellerCheck (String customerId) throws SQLException{
		String seller = customerDao.sellerCheck(customerId);
		
		return seller; //null값 혹은 SELLER 리턴 
	}
	
	/**
	 * 관리자 - 전체회원 리스트 보기
	 **/
	public List<Customer> selectCustomerListAll() throws SQLException {
		List<Customer> CustomerList = customerDao.selectCustomerListAll();
		if(CustomerList == null || CustomerList.isEmpty()) {
			throw new SQLException("----------*****회원정보 불러오기 실패*****----------");
		}
		return CustomerList;
	}
}
