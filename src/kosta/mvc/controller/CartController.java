/**
 * �ۼ��� : ������
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
   * ȸ�� id���� ��ٱ��� ��ȸ
   * */
  public static void cartSelectByCustomerId(String customerId) {
	  try {
			List<Cart> cartList = cartService.cartSelectByCustomerId(customerId);
			EndView.printCartByCustomerId(cartList);
			
			System.out.println("��ٱ��ϸ� �ֹ��Ͻðڽ��ϱ�? (��:1 | �ڷΰ���:0)");
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
   * ��ٱ��� ���
   * */
  public static void insertCart(String customerId, int liquorNo, int cartCount) {
	  try {
		  cartService.insertCart(customerId, liquorNo, cartCount);
		  EndView.meesegePrint("��ϵǾ����ϴ�.");
	  }catch(Exception e) {
		  FailView.errorMessage(e.getMessage());
	  }
  }
  
  /**
   * ��ٱ��� �����ϱ�
   * */
  public static void deleteCart(String customerId) {
	  try {
		  cartService.deleteCart(customerId);
		  EndView.meesegePrint("�����Ǿ����ϴ�.");
	  }catch(Exception e) {
		  FailView.errorMessage(e.getMessage());
	  }
  }

  
  
  /*
   public static void putCart(String customerId, int liquorNo, int cartCount) {
		
		try {
			//��ǰ��ȣ�� �ش� ��ǰã��
			Liquor liquor = LiquorService.liquorSelectByLiquorNo(liquorNo);
			//A01	�����	1500	4	20/09/04
			
			if(liquor.getStock() < cartCount) {
				throw new SQLException("����� �������� ��ٱ��Ͽ� ������ �����ϴ�.");
			}
			//id�� �ش��ϴ� ����ã��
			SessionSet ss = SessionSet.getInstance();
			Session session = ss.get(customerId);	
			
			//���ǿ��� ��ٱ��� ã��
			Map<Liquor, Integer> cart =	(Map<Liquor,Integer>)session.getAttribute("cart"); //��ǰ , ���� ���� 
			
			//��ٱ��ϰ� ������ ��ٱ��� ����
			if(cart == null) { 
				cart = new HashMap<>(); 
				session.setAttribute("cart", cart);
			}
			
			
			//��ٱ��Ͽ��� ��ǰã��
			Integer oldQuantity = cart.get(liquor); //goods�� key ����
			if(oldQuantity != null) { //��ٱ��Ͽ� �̹� ��ǰ�� �ִٸ�
				quantity += oldQuantity; //������ ����
			}
			
			cart.put(liquor, quantity); //��ٱ��Ͽ� ��ǰ �ֱ�
			EndView.printMessage("��ٱ��Ͽ� ��ҽ��ϴ�");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
   
   /**
    * ��ٱ��� ����
    * */
   /*public static void viewCart(String id) {
		SessionSet ss = SessionSet.getInstance();
		Session session = ss.get(id);
		
		Map<Goods,Integer> cart = (Map<Goods, Integer>) session.getAttribute("cart");
		if(cart == null ) { // ��ٱ��ϰ� ���� ����
			FailView.errorMessage("��ٱ��ϰ� ������ϴ�");
		}else {
			EndView.printViewCart(id , cart);
		}
	}*/
}



