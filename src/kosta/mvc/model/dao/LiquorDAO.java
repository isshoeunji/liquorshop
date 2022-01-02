package kosta.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;
import kosta.mvc.model.dto.Liquor;
import kosta.mvc.model.dto.LiquorTable;

public interface LiquorDAO {
	/*
		전체검색 
		List<Liquor> goodsSelect() throws SQLException;
	*/
	
	/**
	 * 가격대별 검색 
	 * */
	List<Liquor> liquorsSelectByLiquorPrice(int cost) throws SQLException;
	
	/**
	 * 양주 카테고리
	 * */
	List<LiquorTable> selectLiquorTable() throws SQLException;
	
	/**
	 * 종류별 검색 
	 * */
	List<Liquor> liquorsSelectByLiquorType(int liqourTableNo) throws SQLException;
	
	
	/**
	 * 번호(PK)에 해당하는 정보 검색
	 * */
	Liquor liquorSelectByLiquorNo(int liquorNo) throws SQLException;
	
	/**
	 * 양주명으로 검색 
	 * */
	Liquor liquorSelectByLiquorName(String name) throws SQLException;
		
	/**
	 * 양주 등록하기 
	 * */
	int insertLiquor(Liquor liquorDTO) throws SQLException;
	
		
	/**
	 * 양주 수정하기 
	 * */
	int updateLiquor(Liquor liquorDTO) throws SQLException;
	
		
	/**
	 * 양주 삭제하기 
	 * */
	int deleteLiquor(int liquorNo) throws SQLException;
	
	
	/**
	 * 양주 수량 변경하기 
	 * */
	int updateStock(Liquor liquorDTO) throws SQLException;
}
