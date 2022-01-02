package kosta.mvc.view;

import java.util.Scanner;

import kosta.mvc.controller.CartController;
import kosta.mvc.controller.CouponController;
import kosta.mvc.controller.CustomerController;
import kosta.mvc.controller.LiquorController;
import kosta.mvc.controller.OrderController;
import kosta.mvc.controller.ReviewController;
import kosta.mvc.model.dto.OrderDetail;
import kosta.mvc.model.dto.Orders;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;

public class CustomerMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * 회원 로그인시
	 * */
	public static void customerMenu(String customerId) {
		CouponController.insertCouponTable(customerId, 10);
		while(true) {
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet()); //Set객체
			System.out.println("-----" +customerId+ " 로그인 중 -----");
			System.out.println("1.카테고리별 상품보기  |  2.가격대별 상품검색  |  3.상품명 검색  | 4.주문하기  |  5.장바구니 담기  |  6.장바구니 보기  |  7.리뷰페이지  |  8.마이페이지 |  0.로그아웃");
			int menu =Integer.parseInt( sc.nextLine());
			
			
			switch(menu) {
				//카테고리 상품보기 
				case 1 :
					LiquorController.selectLiquorTable();
					System.out.println("양주종류 번호를 입력하세요> ");
					int liquorTableNo =Integer.parseInt(sc.nextLine());
					LiquorController.liquorsSelectByLiquorType(liquorTableNo);
					break;
				//가격대별 검색 
				case 2 :
					System.out.println("원하시는 가격대를 입력하세요> ");
					int price =Integer.parseInt(sc.nextLine());
					LiquorController.liquorsSelectByLiquorPrice(price);
					break;
				
				//상품명 검색 
				 case 3 :
					System.out.println("상품명을 입력하세요> ");
					String name = sc.nextLine();
					LiquorController.liquorSelectByLiquorName(name);
					break;
					
				//주문하기
				case 4 :
					printInputOrder(customerId);
					break;
				
				//장바구니 담기
				case 5 :
					putCart(customerId);
					//OrderController.selectOrdersByUserId(customerId);
					break;
				
				//장바구니 보기  
				case 6 :
					viewCart(customerId);
					break;	
				
				//리뷰 검색 
				case 7 : 
					reviewMenu(customerId);
					break;
				
				//마이페이지 
				case 8 : 
					myPageMenu(customerId);
					break;
					
				//로그아웃 
				case 0 : 
					logout(customerId);
					MenuView.menu();
					break;
					
				default:
					System.out.println("번호를 다시 확인해주세요.");
				}
		}
		
	}
	
	/**
	 * 4. 주문하기
	 * */
    public static void printInputOrder(String customerId) {
    	System.out.print("주문상품번호 : ");
		int liquorNo =Integer.parseInt(sc.nextLine());
		 
		System.out.print("주문수량 : ");
		int count = Integer.parseInt(sc.nextLine());
		 
		System.out.print("배송주소 : ");
		String orderAddr = sc.nextLine();

		System.out.print("쿠폰을 사용하시겠습니까?(yes or no) : ");
		String use = sc.nextLine();

		int couponNo = 0;
		if (use.equals("yes")) {
			CouponController.couponSelectAll(customerId);
			System.out.print("쿠폰 코드 입력 : ");
			couponNo = Integer.parseInt(sc.nextLine());
		}

		Orders orders = new Orders(0, customerId, null, orderAddr, null, 0, 0);
		OrderDetail orderDetail = new OrderDetail(0, liquorNo, 0, count, 0);

		orders.getOrderDetailList().add(orderDetail);

		OrderController.insertOrders(orders, couponNo);
		CouponController.useCoupon(couponNo); 
    }
	
    /**
     * 5. 장바구니 담기
     * */
    public static void putCart(String id) {
		System.out.println("--장바구니 담기 작업 --");
		//System.out.print("아이디를 입력하세요 : ");
		//String customerId = sc.nextLine();
		System.out.println("양주번호 : ");
		int liquorNo = Integer.parseInt(sc.nextLine());
		System.out.print("수량 : ");
		int cartCount = Integer.parseInt(sc.nextLine());
		
		CartController.insertCart(id,liquorNo,cartCount);
	}
    
    
    /**
     * 6. 장바구니 보기
     * */
	public static void viewCart(String id) {
		System.out.println("--장바구니 보기--");
		
		CartController.cartSelectByCustomerId(id);
		
		/*
		 * 장바구니 보여주고...
		 * 스캐너 입력 받아서 
		 * 주문할꺼면 1번 입력, 돌아가기 0번 이런식으로 입력 받아야 할거같아요.
		 * 
		 * 참고로,1번 입력 받으면..
		 * 오더 컨트롤러의 
		 * OrderCart(String customerId, String addr, int couponNo) <- 요 메소드 사용해야되니까...
		 * 
		 * 추가로 배송 주소, 쿠폰 번호 입력 받아주시면 될듯.
		 * */
		
//		System.out.println("장바구니를 주문하시겠습니까? (예:1 | 뒤로가기:0)");
//		int input = Integer.parseInt(sc.nextLine());
//		
//		if(input == 1) {//입력값이 1이면...
//			System.out.print("배송 주소 :");
//			String addr = sc.nextLine();
//			System.out.print("쿠폰을 사용하시겠습니까?(yes or no) : ");
//			String use = sc.nextLine();
//
//			int couponNo = 0;
//			if (use.equals("yes")) {
//				CouponController.couponSelectAll(id);
//				System.out.print("쿠폰 코드 입력 : ");
//				couponNo = Integer.parseInt(sc.nextLine());
//			}
//			
//			//장바구니로 주문하는 오더 컨트롤러의 메소드 호출.
//			OrderController.OrderCart(id, addr, couponNo);
//			//id -> 세션에서 갖고옴
//			//addr -> 스캐너에서 입력 받음
//			//couponNo -> 스캐너에서 입력 받음(없으면 0 입력)
//			CouponController.useCoupon(couponNo);
//			
//		}else {//입력값이 1이 아니면...
//			//다시 고객 메뉴 띄우기
//			customerMenu(id);
//		}
		
		
	}
	
	/**
	 * 장바구니로 주문하기
	 * */
	public static void orderCart(String id, int input) {
		
		
		if(input == 1) {//입력값이 1이면...
			System.out.print("배송 주소 :");
			String addr = sc.nextLine();
			System.out.print("쿠폰을 사용하시겠습니까?(yes or no) : ");
			String use = sc.nextLine();

			int couponNo = 0;
			if (use.equals("yes")) {
				CouponController.couponSelectAll(id);
				System.out.print("쿠폰 코드 입력 : ");
				couponNo = Integer.parseInt(sc.nextLine());
			}
			
			//장바구니로 주문하는 오더 컨트롤러의 메소드 호출.
			OrderController.OrderCart(id, addr, couponNo);
			//id -> 세션에서 갖고옴
			//addr -> 스캐너에서 입력 받음
			//couponNo -> 스캐너에서 입력 받음(없으면 0 입력)
			CouponController.useCoupon(couponNo);
			
		}else {//입력값이 1이 아니면...
			//다시 고객 메뉴 띄우기
			customerMenu(id);
		}
	}
    
	
	/**
	 * 7. 리뷰 메뉴
	 * */
	public static void reviewMenu(String customerId) {
		System.out.println("-----리뷰 페이지-----");
		System.out.println("1.전체리뷰 조회  |  2.양주별 리뷰조회   |  3.회원별 리뷰조회  |  4.나가기");  
		int menu =Integer.parseInt(sc.nextLine());
		
		switch(menu) {
		//전체리뷰조회
		case 1:
			ReviewController.reviewSelectAll();
			reviewMenu(customerId);
			break;
			
		//양주별 리뷰 조회
		case 2:
			System.out.println("양주번호를 입력하세요 : ");
			int liquorNo = Integer.parseInt(sc.nextLine());
			ReviewController.reviewSelectByLiquorNo(liquorNo);
			reviewMenu(customerId);
			break;
			
		//회원별 리뷰 조회
		case 3:
			System.out.println("회원ID를 입력하세요 : ");
			String id = sc.nextLine();
			ReviewController.reviewSelectByCustomerId(id);
			reviewMenu(customerId);
			break;
			
			//나가기 
		case 4:
			customerMenu(customerId);
			break;

		default:
			System.out.println("번호를 다시 확인해주세요.");
			
		}
	}
    
	/**
	 * 8. 마이페이지 메뉴
	 * */
	public static void myPageMenu(String customerId) {
		System.out.println("-----마이페이지-----");
		System.out.println("1.주문내역 확인   |  2.리뷰 작성  |   3.주문 취소   |  4.장바구니 비우기  |  5.쿠폰리스트 확인  |  6.회원정보 수정  |  7.회원 탈퇴  |  0. 나가기");  
		int menu =Integer.parseInt( sc.nextLine());
		
		switch(menu) {
			//주문내역 확인 
			case 1:
				OrderController.selectOrdersByUserId(customerId);
				myPageMenu(customerId);
				break;
				
			//2.리뷰작성
			case 2:
				putReview(customerId);
				myPageMenu(customerId);
				break;
				
			//주문 취소 
			case 3:
				deleteOrder();
				myPageMenu(customerId);
				break;
				
			//장바구니 비우기 
			case 4:
				deleteCart(customerId);
				myPageMenu(customerId);
				break;
				
			//쿠폰리스트 확인 
			case 5:
				CouponController.couponSelectAll(customerId);
				myPageMenu(customerId);
				break;
				
			//회원정보 수정 
			case 6:
				updateCustomer(customerId);
				myPageMenu(customerId);
				break;
			
			//회원정보 확인 
			//case 7:
			//	break;
				
			//회원 탈퇴 
			case 7:
				CustomerController.deleteCustomer(customerId);
				MenuView.menu();
				
			//나가기 
			case 0:
				customerMenu(customerId);
				break;

			default:
				System.out.println("번호를 다시 확인해주세요.");
		}
	}
	
	/**
	 * 0. 로그아웃
	 * */
	public static void logout(String customerId) {
		Session session = new Session(customerId);
		
		SessionSet ss = SessionSet.getInstance();
		ss.remove(session);	
	}
	
	
	/////////////마이페이지//////////////
	/**
	 * 2. 리뷰 작성
	 * */
	public static void putReview(String id) {
		System.out.println("--리뷰 작성하기 --");
		System.out.println("양주번호 : ");
		int liquorNo = Integer.parseInt(sc.nextLine());
		System.out.print("내용을 입력하세요 : ");
		String content = sc.nextLine();
		
		ReviewController.insertReview(liquorNo, id, content);
	}
	
	/**
	 * 3. 주문 취소
	 * */
	public static void deleteOrder() {
		System.out.print("취소하려는 주문 번호를 입력해주세요: ");
		int orderNo = Integer.parseInt(sc.nextLine());
		
		OrderController.deleteOrder(orderNo);
	}
	
	/**
	 * 4. 장바구니 비우기
	 * */
	public static void deleteCart(String id) {
		//System.out.println("삭제할 장바구니의 회원ID를 입력하세요");
		//String customerId = sc.nextLine();
		
		CartController.deleteCart(id);
	}
	
	
	/**
	 * 5. 회원정보 수정 
	 * */
	public static void updateCustomer(String customerId) {
		System.out.println("1.회원정보 보기  | 2. 비밀번호 수정   |  3.이메일 수정   |  4.주소 수정 |  0. 나가기");
		System.out.println("수정하실 정보를 선택해주세요: ");
		int menu =Integer.parseInt( sc.nextLine());
		
		switch(menu) {
			case 1:
				//회원 정보 보여주는 컨트롤러 메소드 띄우고...
				CustomerController.selectCustomerByCustomerId(customerId);
				//보여줬으면 다시 회원정보 수정 메뉴 띄워주기.
				updateCustomer(customerId);
				break;
		
			//비밀번호 수정 
			case 2:
				System.out.print("새로운 비밀번호를 입력해주세요: ");
				String pwd = sc.nextLine();
				CustomerController.updateCustomerPwd(customerId, pwd);
				break;
			
			//이메일 수정 
			case 3: 
				System.out.print("새로운 이메일을 입력해주세요: ");
				String email = sc.nextLine();
				CustomerController.updateCustomerEmail(customerId, email);
				break;
			
			//주소 수정
			case 4:
				System.out.print("새로운 주소를 입력해주세요: ");
				String addr = sc.nextLine();
				CustomerController.updateCustomerAddr(customerId, addr);
				break;
		
			//나가기 
			case 0:
				myPageMenu(customerId);
				break;
				
			default:
				System.out.println("번호를 다시 확인해주세요.");
		}
	}
	
	/**
	 * 6. 회원정보 확인 
	 * */
	public static void checkCustomerInfo(String customerId) {
		
	}

}
