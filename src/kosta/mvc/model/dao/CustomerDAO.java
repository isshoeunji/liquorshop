package kosta.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.Customer;
import kosta.mvc.model.dto.LiquorTable;

public interface CustomerDAO {

/**
 * 회원가입하기	
 **/
	int insertCustomer(Customer customer) throws SQLException;
	
/**
 * 회원 아이디 중복 체크	
 **/
	String customerIdDuplicationCheck(String customerId) throws SQLException;
	
	
/**
 * 로그인하기
 **/
   Customer customerLogin(String customerId, String customerpwd)throws SQLException;

/**
 * 	회원정보찾기(아이디)
 **/
	String findCustomerId (String email) throws SQLException;

/**
 * 	 회원정보찾기(비번) 
 **/
	String findCustomerPwd (String customerId, String email) throws SQLException;

/**
 * 	회원정보찾기(이름) -> 아이디를 넣으면 이름이 나온다. 
 * */
	String findCustomerName (String customerId) throws SQLException;

/**
 * 	회원정보수정(이메일)
 **/
	int updateCustomerEmail (String customerId, String email) throws SQLException;
	
/**
 *  회원정보수정(비번)
 **/
	int updateCustomerPwd (String customerId, String pwd) throws SQLException;
	
/**
 *  회원정보수정(주소) 	
 **/
	int updateCustomerAddr (String customerId, String addr) throws SQLException;

/**
 * 회원 탈퇴  
 **/
	int deleteCustomer (String customerId) throws SQLException;
/**
 * 회원 정보 확인
 * (아이디를 넣으면 -> Customer 객체(DTO)를 돌려주는 메소드!)
 * */
	Customer selectCustomerByCustomerId(String customerId) throws SQLException;

////////////////////////////////////////////////////////////
	/**
 * 회원인지 판매자 확인  
 * @return seller일 경우 "SELLER"(대문자 유의!) 값 리턴. 아닐 경우 null값 리턴  
 **/
	String sellerCheck (String customerId) throws SQLException;
	
/**
 * 	관리자 - 회원메뉴- 전체회원 리스트 보기
 **/
	List<Customer> selectCustomerListAll() throws SQLException;
	
/**
 * 관리자 -회원받아서 탈퇴시켜버린다.
 **/

	
}
