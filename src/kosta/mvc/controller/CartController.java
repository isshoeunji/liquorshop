/**
 * 작성자 : 서은지
 * */
package kosta.mvc.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kosta.mvc.model.dto.Cart;
import kosta.mvc.model.dto.Liquor;
import kosta.mvc.model.service.CartService;
import kosta.mvc.model.service.LiquorService;
import kosta.mvc.session.Session;
import kosta.mvc.session.SessionSet;
import kosta.mvc.view.CustomerMenuView;
import kosta.mvc.view.EndView;
import kosta.mvc.view.FailView;


public class CartController {
  private static CartService cartService = new CartService();
  
  /**
   * 회원 id별로 장바구니 조회
   * */
  public static void cartSelectByCustomerId(String customerId) {
	  try {
			List<Cart> cartList = cartService.cartSelectByCustomerId(customerId);
			EndView.printCartByCustomerId(cartList);
			
			System.out.println("장바구니를 주문하시겠습니까? (예:1 | 뒤로가기:0)");
			Scanner sc = new Scanner(System.in);
			int input = Integer.parseInt(sc.nextLine());
			if(input == 1) {
				CustomerMenuView.orderCart(customerId, input);
			}else if(input == 0) {
				CustomerMenuView.customerMenu(customerId);
			}
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
  }
  
  /**
   * 장바구니 담기
   * */
  public static void insertCart(String customerId, int liquorNo, int cartCount) {
	  try {
		  cartService.insertCart(customerId, liquorNo, cartCount);
		  EndView.meesegePrint("등록되었습니다.");
	  }catch(Exception e) {
		  FailView.errorMessage(e.getMessage());
	  }
  }
  
  /**
   * 장바구니 삭제하기
   * */
  public static void deleteCart(String customerId) {
	  try {
		  cartService.deleteCart(customerId);
		  EndView.meesegePrint("삭제되었습니다.");
	  }catch(Exception e) {
		  FailView.errorMessage(e.getMessage());
	  }
  }

  
  
  /*
   public static void putCart(String customerId, int liquorNo, int cartCount) {
		
		try {
			//상품번호에 해당 상품찾기
			Liquor liquor = LiquorService.liquorSelectByLiquorNo(liquorNo);
			//A01	새우깡	1500	4	20/09/04
			
			if(liquor.getStock() < cartCount) {
				throw new SQLException("재고량 부족으로 장바구니에 담을수 없습니다.");
			}
			//id에 해당하는 세션찾기
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(customerId);	
			
			//세션에서 장바구니 찾기
			Map<Liquor, Integer> cart =	(Map<Liquor,Integer>)session.getAttribute("cart"); //상품 , 수량 저장 
			
			//장바구니가 없으면 장바구니 생성
			if(cart == null) { 
				cart = new HashMap<>(); 
				session.setAttribute("cart", cart);
			}
			
			
			//장바구니에서 상품찾기
			Integer oldQuantity = cart.get(liquor); //goods는 key 정보
			if(oldQuantity != null) { //장바구니에 이미 상품이 있다면
				quantity += oldQuantity; //수량을 누적
			}
			
			cart.put(liquor, quantity); //장바구니에 상품 넣기
			EndView.printMessage("장바구니에 담았습니다");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
   
   /**
    * 장바구니 보기
    * */
   /*public static void viewCart(String id) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		
		Map<Goods,Integer> cart = (Map<Goods, Integer>) session.getAttribute("cart");
		if(cart == null ) { // 장바구니가 없는 고객
			FailView.errorMessage("장바구니가 비었습니다");
		}else {
			EndView.printViewCart(id , cart);
		}
	}*/
}




