package kosta.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.Customer;
import kosta.mvc.model.dto.Liquor;
import kosta.mvc.model.service.CustomerService;
import kosta.mvc.view.AdminMenuView;
import kosta.mvc.view.CustomerEndView;
import kosta.mvc.view.CustomerMenuView;
import kosta.mvc.view.EndView;
import kosta.mvc.view.FailView;
import kosta.mvc.view.LiquorEndView;
import kosta.mvc.view.MenuView;

public class CustomerController {
	static CustomerService customerService = new CustomerService();
	
	/////////////////////테스트//////////////////////
	/*public static void main(String[] args) {
		//login("YOO", "2222");
		//login("ADMIN", "1234");
		//findCustomerId("DDD444@naver.com");
	}*/
	////////////////////////////////////////////////
	
 /**
  * 로그인
  * */
	public static void login(String customerId, String customerPwd) {
		try {
			Customer customer = customerService.customerLogin(customerId, customerPwd); 
			sellerCheck(customerId);
		}catch (Exception e) {
			FailView.errorMessage("로그인에 실패했어요.");
		}
	}
	
	/**
	 * 회원가입
	 * */
    public static void register(Customer customer) {
    	try {
			customerService.register(customer);
			CouponController.insertCouponTable(customer.getCustomerId(), 15);
			EndView.printMessage("회원가입에 성공했어요.");
		}catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
    }
    
    
    /**
     * 회원정보찾기 (아이디) 
     **/
    public static String findCustomerId(String customerEmail) {
    	String id = null;
    	try {
    		id = customerService.findCustomerId(customerEmail);
    		return id;
    	}catch(Exception e) {
    		FailView.errorMessage(e.getMessage());
    	}
    	return null;
    }


    /**
     * 회원정보찾기 (비번) 
     **/
    public static String findcustomerPwd(String customerId, String email) {
    	String pwd = null;
    	try {
    		pwd = customerService.findCustomerPwd(customerId, email);
    		return pwd;
    	}catch(Exception e) {
    		FailView.errorMessage(e.getMessage());
    	}
    	return null;
    }

    /**										 
     * 회원정보수정(이메일) 
     **/
    public static void updateCustomerEmail(String customerId, String email) {
    	try {
    		int result = customerService.updateCustomerEmail(customerId,email);
    		EndView.printMessage("이메일이 수정되었어요.");
    	}catch(Exception e) {
    		e.printStackTrace();
    		FailView.errorMessage(e.getMessage());
    	}   
    }


    /**
     * 회원정보수정 (비번) 
     **/
    public static void updateCustomerPwd(String customerId, String pwd) {
    	try {
    		int result = customerService.updateCustomerPwd(customerId, pwd);    
    		EndView.printMessage("비밀번호가 수정되었어요.");
    	}catch(Exception e) {
    		e.printStackTrace();
    		FailView.errorMessage(e.getMessage());
    	}
    }
    
    
    /**
     * 회원정보수정(주소)
     **/
    public static void updateCustomerAddr(String customerId, String Addr) {
    	try {
    		int result = customerService.updateCustomerAddr(customerId, Addr);
    		EndView.printMessage("주소가 수정되었어요.");
    	}catch(Exception e) {
    		e.printStackTrace();
    		FailView.errorMessage(e.getMessage());
    	}
    }
    
    
    

    /**
     * 회원탈퇴
     **/
    
    public static void deleteCustomer(String customerId) {
    	try {
    		int id = customerService.deleteCustomer(customerId);
    		EndView.printMessage("탈퇴되었어요. 다음에 또 만나요!");
    	}catch(Exception e) {
    		FailView.errorMessage(e.getMessage());
    	}
    }
    
    /**
     * 회원 정보 확인
     * */
    public static void selectCustomerByCustomerId(String customerId){
    	try {
			//서비스에서 커스터머 객체를 가져와서...
    		Customer customer = customerService.selectCustomerByCustomerId(customerId);
			//customer 객체를 입력받아서 화면에다가 회원정보를 뿌려주는 메소드를 불러옴.
    		CustomerEndView.printCustomer(customer);
			//이러면 컨트롤러가 하는 일은 끝!
		} catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
    }
    
    /**
	 * 회원인지 판매자 확인  
	 **/
	public static void sellerCheck (String customerId){
		try {
			//seller가 지금 null이 나와서 nullPointerException 
			//-> seller가 null이 안나오면 된다!
			//-> seller를 null로 안넣고, customer라는 값을 부여했다. 
			//만들어놓은 레코드들을 다 삭제를 한 후에, 테이블에 있는 default값 자체를 null -> customer로 기본값을 변경하고
			//그 후에 레코드들을 다시 넣었다. 
			String seller = customerService.sellerCheck(customerId);
			if(seller.equals("SELLER")) {
				AdminMenuView.sellerMenu(customerId);
			}else {
				CustomerMenuView.customerMenu(customerId);
			}
		} catch (Exception e) {
			FailView.errorMessage("");
		}
	}
	
	public static void selectCustomerListAll(){
		try {
			List<Customer> customerList = customerService.selectCustomerListAll();
			CustomerEndView.printselectCustomerListAll(customerList);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	
}
