package kosta.mvc.view;


import java.util.Scanner;

import kosta.mvc.controller.CustomerController;
import kosta.mvc.model.dto.Customer;
import kosta.mvc.session.SessionSet;

public class MenuView {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * 첫 화면
	 * */
	public static void menu() {
		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet());
			
			System.out.println("=== KOSTA liqour Shopping Mall ===");
			System.out.println("1.회원가입   |   2.로그인   |   3.아이디 찾기   |   4.비밀번호 찾기   |  0.종료");
			
			int menu = Integer.parseInt(sc.nextLine());
			switch(menu) {
			//회원가입
			case 1 :
				register(); 
				break;
			//로그인
			case 2 :
				login();
				break;
				
			//아이디 찾기 
			case 3 :
				findId();
				break;
				
			//비밀번호 찾기 
			case 4 :
				findPwd();
				break;
			
			//종료하기
			case 0 : 
				System.exit(0);
			
			default:
				System.out.println("번호를 다시 확인해주세요.");
			}
		}
	}
	
	
	/**
	 * 1. 회원가입
	 * */
	public static void register() {
		System.out.print("아이디 : ");
		 String customerId = sc.nextLine();
		 
		 System.out.print("비밀번호 : ");
		 String pwd = sc.nextLine();
		 
		 System.out.print("이름 : ");
		 String customerName = sc.nextLine();
		 
		 System.out.print("생일(년도/월/일) :   예시) 2000/08/09");
		 String birth = sc.nextLine();
		 
		 System.out.print("이메일 : ");
		 String email = sc.nextLine();
		 
		 System.out.print("주소 : ");
		 String addr = sc.nextLine();
		 
		 System.out.print("연락처 : ");
		 String contact = sc.nextLine();
		 
		 CustomerController.register(new Customer(customerId, pwd, customerName, birth, email, addr, contact, addr, contact));
	}
	
	
	/**
	 * 2. 로그인
	 * */
	public static void login() {
		 System.out.print("아이디 : ");
		 String customerId = sc.nextLine();
		 
		 System.out.print("비번 : ");
		 String customerPwd = sc.nextLine();
		 
		 CustomerController.login(customerId, customerPwd); 
	}
	
	
	/**
	 * 3. 아이디 찾기
	 * */
	public static void findId() {
		System.out.print("이메일 : ");
		String email = sc.nextLine();
		
		System.out.println("찾는 아이디는 다음과 같아요: "+ CustomerController.findCustomerId(email));
		//CustomerController.findCustomerId(email);
	}
	
	
	/**
	 * 4. 비밀번호 찾기
	 * */
	public static void findPwd() {
		System.out.print("아이디: ");
		String customerId = sc.nextLine();
		
		System.out.println("이메일: ");
		String email = sc.nextLine();
		
		System.out.println("찾은 비밀번호는 다음과 같아요: " + CustomerController.findcustomerPwd(customerId, email));
	}
}



