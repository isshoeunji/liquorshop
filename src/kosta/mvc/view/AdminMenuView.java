package kosta.mvc.view;

import java.util.List;
import java.util.Scanner;

import kosta.mvc.controller.CustomerController;
import kosta.mvc.controller.LiquorController;
import kosta.mvc.controller.OrderController;
import kosta.mvc.controller.ReviewController;
import kosta.mvc.model.dto.Customer;
import kosta.mvc.model.dto.Liquor;
import kosta.mvc.model.dto.Orders;
import kosta.mvc.model.dto.Review;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;

public class AdminMenuView {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * 판매자용 메뉴
	 * */
	public static void sellerMenu(String sellerId) {
		while(true) {//무한루프
			//세션 정보 (작동 확인 테스트 후 주석처리 하기!!)
			SessionSet ss = SessionSet.getInstance();
			System.out.println(ss.getSet()); //Set객체
			System.out.println("-----" +sellerId+ " 로그인 중 -----");
			//세션 정보 (작동 확인 테스트 후 주석처리 하기!!)
			
			System.out.println("-- 관리자 메뉴 --");
			System.out.println("1.양주메뉴  |  2.주문메뉴  |  3.회원메뉴  |  4.리뷰메뉴  |  0. 로그아웃");
			int menu =Integer.parseInt(sc.nextLine());
			switch(menu) {
				//양주 메뉴 
				case 1:
					liquorAdminMenu(sellerId);
					break;
					
				//주문 메뉴 
				case 2:
					orderAdminMenu(sellerId);
					break;
					
				//회원메뉴 
				case 3:
					customerAdminMenu(sellerId);
					break;
					
				//리뷰메뉴
				case 4:
					reviewAdminMenu(sellerId);
					break;
				
				//나가기
				case 0:
					//CustomerMenuView.logout(sellerId);
					logout(sellerId);
					MenuView.menu();
					break;
					
				default:
					System.out.println("번호를 다시 확인해주세요.");
			}
		}
	}
	
	/////////////////////////////////////////////////////////////////////////
	/**
	 * 1. 양주 관리 메뉴
	 * */
	public static void liquorAdminMenu(String sellerId) {
		while(true) {//무한루프
			System.out.println("-- 양주 관리 메뉴 --");
			System.out.println("1.카테고리별 보기  |  2.양주 이름 검색  |  3.양주 추가  |  4.양주 수정  |  5.양주 삭제  |  0.나가기");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				//카테고리별 보기 
				case 1:
					LiquorController.selectLiquorTable();
					System.out.print("양주종류 번호를 입력하세요> ");
					int liquorTableNo =Integer.parseInt(sc.nextLine());
					LiquorController.liquorsSelectByLiquorType(liquorTableNo);
					break;
					
				//양주 이름 검색 
				case 2:
					System.out.print("상품명을 입력하세요> ");
					String name = sc.nextLine();
					LiquorController.liquorSelectByLiquorName(name);
					break;
					
				//양주 추가 
				case 3:
					System.out.print("양주종류 번호: ");
					liquorTableNo = Integer.parseInt(sc.nextLine());
					
					System.out.print("양주 이름: ");
					String liquorName = sc.nextLine();
					 
					System.out.print("양주 가격 : ");
					int liquorPrice = Integer.parseInt(sc.nextLine());
					 
					System.out.print("등록일자 : ");
					String addDate = sc.nextLine();
					 
					LiquorController.insertLiquor(new Liquor(liquorTableNo, liquorName, liquorPrice, addDate));
				
					break;
					
				//양주 수정 
				case 4:
					System.out.print("수정할 양주 번호: ");
					int liquorNo = Integer.parseInt(sc.nextLine());
					
					System.out.print("수정할 양주 이름: ");
					liquorName = sc.nextLine();
					 
					System.out.print("수정할 양주 가격 : ");
					liquorPrice = Integer.parseInt(sc.nextLine());
					
					System.out.print("수정할 양주 재고 : ");
					int stock = Integer.parseInt(sc.nextLine());
					
					LiquorController.updateLiquor(new Liquor(liquorNo, liquorName, liquorPrice, stock));
					break;
				
				//양주 삭제 
				case 5:
					System.out.println("삭제할 양주번호: ");
					liquorNo = Integer.parseInt(sc.nextLine());
					System.out.println("정말 삭제하시겠습니까?   1.Yes   2.No");
					int select = Integer.parseInt(sc.nextLine());
					switch(select) {
						case 1:
							LiquorController.deleteLiquor(liquorNo);
						case 2:
							break;
						default:
							System.out.println("입력한 번호를 다시 확인해주세요.");
					}
					break;
				
				//나가기
				case 0:
					sellerMenu(sellerId);
					break;
				
				default:
					System.out.println("번호를 다시 확인해주세요.");
			}
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////////
	
	/**
	 * 2. 주문 관리 메뉴
	 * */
	public static void orderAdminMenu(String sellerId) {
		while(true) { //무한루프
			System.out.println("-- 주문 관리 메뉴 --");
			System.out.println("1.전체 주문 리스트  |  2.회원번호로 주문 검색  |  3.주문 상태 수정  |  4.주문 삭제  |  0.나가기");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				//전체 주문 리스트 
				case 1:
					OrderController.orderSelectAll();
					break;
					
				//회원번호로 주문 검색 
				case 2:
					selectOrdersByUserId();
					break;
					
				//주문 수정 
				case 3:
					updateOrder();
					break;
					
				//주문 삭제 
				case 4:
					deleteOrder();
					break;
				
				//나가기
				case 0:
					sellerMenu(sellerId);
					break;
				
				default:
					System.out.println("번호를 다시 확인해주세요.");
			}
		}
	}
	
	/**
	 * 회원별 주문 조회
	 * */
	public static void selectOrdersByUserId() {
		System.out.print("주문 조회하려는 회원의 아이디를 입력해주세요: ");
		String id = sc.nextLine();
		
		OrderController.selectOrdersByUserId(id);
	}
	
	/**
	 * 주문 상태 수정
	 * */
	public static void updateOrder() {
		System.out.print("상태를 수정하려는 주문 번호를 입력해주세요: ");
		int orderNo = Integer.parseInt(sc.nextLine());
		System.out.print("상태를 입력해주세요: ");
		String status = sc.nextLine();
		
		OrderController.updateOrder(orderNo, status);
	}
	
	/**
	 * 주문 삭제
	 * */
	public static void deleteOrder() {
		System.out.print("취소하려는 주문 번호를 입력해주세요: ");
		int orderNo = Integer.parseInt(sc.nextLine());
		
		OrderController.deleteOrder(orderNo);
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	/**
	 * 3. 회원 관리 메뉴
	 * */
	public static void customerAdminMenu(String sellerId) {
		while(true) {
			System.out.println("-- 회원 관리 메뉴 --");
			System.out.println("1.전체회원 리스트  |  2.회원 강퇴  |  0.나가기");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				//전체 회원 리스트 
				case 1:
					CustomerController.selectCustomerListAll();
					break;
					
				//회원 강퇴 
				case 2:
					System.out.print("삭제할 회원 아이디를 입력하세요: ");
					String customerId = sc.nextLine();
					CustomerController.deleteCustomer(customerId);
					break;
					
				//나가기
				case 0:
					sellerMenu(sellerId);
					break;
				
				default:
					System.out.println("번호를 다시 확인해주세요.");
			}
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////////
	
	/**
	 * 4. 리뷰 관리 메뉴
	 * */
	public static void reviewAdminMenu(String sellerId) {
		while(true) {
			System.out.println("-- 리뷰 관리 메뉴 --");
			System.out.println("1.전체 리뷰 조회  |  2.회원별 리뷰 조회 |  3.양주번호별 리뷰 조회  |  4.리뷰 수정  |  5.리뷰 삭제 |  0.나가기");
			int menu =Integer.parseInt( sc.nextLine());
			switch(menu) {
				//전체 리뷰 조회  
				case 1:
					ReviewController.reviewSelectAll();
					break;
					
				//회원별 리뷰 조회 
				case 2:
					reviewSelectByCustomerId();
					break;
					
				//양주번호별 리뷰 조회 
				case 3:
					reviewSelectByLiquorNo();
					break;
					
				//리뷰 수정 
				case 4:
					updateReivew();
					break;
				
				//리뷰 삭제 
				case 5:
					deleteReivew();
					break;
				
				//나가기
				case 0:
					sellerMenu(sellerId);
					break;
				
				default:
					System.out.println("번호를 다시 확인해주세요.");
			}
		}
	}
	
	/**
	 * 회원별 리뷰 조회
	 * */
	public static void reviewSelectByCustomerId() {
		System.out.print("조회하려는 회원의 아이디를 입력해주세요: ");
		String id = sc.nextLine();
		
		ReviewController.reviewSelectByCustomerId(id);
	}
	
	/**
	 * 양주별 리뷰 조회
	 * */
	public static void reviewSelectByLiquorNo() {
		System.out.print("조회하려는 양주의 번호를 입력해주세요: ");
		int no = Integer.parseInt(sc.nextLine());
		
		ReviewController.reviewSelectByLiquorNo(no);
	}
	
	/**
	 * 리뷰 수정
	 * */
	public static void updateReivew() {
		System.out.println("리뷰를 변경하려는 리뷰의 번호를 입력하세요: ");
		int reviewNo = Integer.parseInt(sc.nextLine()); 
		System.out.println("새로운 리뷰 내용을 입력하세요: ");
		String content = sc.nextLine();
		
		//Review review = new Review(content, reviewNo);
		ReviewController.updateReview(content, reviewNo);
	}
	
	/**
	 * 리뷰 삭제
	 * */
	public static void deleteReivew() {
		System.out.println("삭제하려는 리뷰의 번호를 입력하세요: ");
		int reviewNo = Integer.parseInt(sc.nextLine()); 
		
		ReviewController.deleteReview(reviewNo);
	}
	
	//////////////////////////////
	/**
	 * 0. 로그아웃
	 * */
	public static void logout(String customerId) {
		Session session = new Session(customerId);
		
		SessionSet ss = SessionSet.getInstance();
		ss.remove(session);	
	}
	
}
