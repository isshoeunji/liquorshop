package kosta.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dto.Liquor;
import kosta.mvc.model.dto.LiquorTable;
import kosta.mvc.model.service.LiquorService;
import kosta.mvc.view.FailView;
import kosta.mvc.view.LiquorEndView;


public class LiquorController {
	static LiquorService liquorService = new LiquorService();
	
	public static void liquorsSelectByLiquorPrice(int price) {
		try {
			List<Liquor> liquorList = liquorService.liquorsSelectByLiquorPrice(price);
			LiquorEndView.printLiquorList(liquorList);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public static void selectLiquorTable() {
		try {
			List<LiquorTable> liquorTable = liquorService.selectLiquorTable();
			LiquorEndView.printLiquorTableList(liquorTable);
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public static void liquorsSelectByLiquorType(int liqourTableNo) {
		try {
			List<Liquor> liquorList = liquorService.liquorsSelectByLiquorType(liqourTableNo);
			LiquorEndView.printLiquorList(liquorList);
		} catch (Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	
	public static void liquorSelectByLiquorNo(int liquorNo){
		try {
			Liquor liquor = liquorService.liquorSelectByLiquorNo(liquorNo);
			LiquorEndView.printLiquor(liquor);
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public static void liquorSelectByLiquorName(String liquorName){
		try {
			Liquor liquor = liquorService.liquorSelectByLiquorName(liquorName);
			LiquorEndView.printLiquor(liquor);
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	public static void insertLiquor(Liquor liquorDTO) {
		try {
			liquorService.insertLiquor(liquorDTO);
			LiquorEndView.messagePrint("상품이 등록되었어요.");
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}


	public static void updateLiquor(Liquor liquorDTO) {
		try {
			liquorService.updateLiquor(liquorDTO);
			LiquorEndView.messagePrint("상품 정보가 수정되었어요.");
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}

	
	public static void deleteLiquor(int liquorNo){
		try {
			liquorService.deleteLiquor(liquorNo);
			LiquorEndView.messagePrint("상품이 삭제되었어요.");
		}catch(SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
}
