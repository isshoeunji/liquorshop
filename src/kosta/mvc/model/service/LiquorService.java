package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dao.LiquorDAO;
import kosta.mvc.model.dao.LiquorDAOImpl;
import kosta.mvc.model.dto.Liquor;
import kosta.mvc.model.dto.LiquorTable;

public class LiquorService {
	LiquorDAO liquorDao = new LiquorDAOImpl();
	/**
	 * 가격대별 검색 
	 * */
	public List<Liquor> liquorsSelectByLiquorPrice(int price) throws NotFoundException, SQLException{
		List<Liquor> list = liquorDao.liquorsSelectByLiquorPrice(price);
		if(list.size()==0)throw new NotFoundException("해당 가격대에 해당하는 상품이 없어요.");
		return list;
	}
	
	/**
	 * 양주 카테고리 목록 보여주기 
	 * */
	public List<LiquorTable> selectLiquorTable() throws SQLException{
		List<LiquorTable> list = liquorDao.selectLiquorTable();
		return list;
	}
	
	/**
	 * 종류별 검색 (양주 종류번호 필요) 
	 * */
	public List<Liquor> liquorsSelectByLiquorType(int liqourTableNo) throws NotFoundException, SQLException{
		List<Liquor> list = liquorDao.liquorsSelectByLiquorType(liqourTableNo);
		if(list.size()==0) {
			throw new NotFoundException("해당 종류에 해당하는 상품이 없어요.");
		}
		return list;
	}
	
	/**
	 * 번호(PK)에 해당하는 정보 검색
	 * */
	public Liquor liquorSelectByLiquorNo(int liquorNo) throws SQLException{
		Liquor liquor = liquorDao.liquorSelectByLiquorNo(liquorNo);
		if(liquor==null)throw new SQLException(liquorNo + "번에 해당하는 상품이 없어요.");
		return liquor;
	}
	
	/**
	 * 양주명으로 정보 검색 
	 * */
	public Liquor liquorSelectByLiquorName(String liquorName) throws SQLException{
		Liquor liquor = liquorDao.liquorSelectByLiquorName(liquorName);
		if(liquor==null)throw new SQLException(liquorName + "에 해당하는 상품이 없어요.");
		return liquor;
	}
	
		
	/**
	 * 양주 등록하기 
	 * */
	public int insertLiquor(Liquor liquorDTO) throws SQLException{
		int result = liquorDao.insertLiquor(liquorDTO);
		if(result == 0) throw new SQLException("등록에 실패했어요.");
		return result;
	}
	
	
	/**
	 * 양주 수정하기 
	 * */
	public int updateLiquor(Liquor liquorDTO) throws SQLException{
		int result = liquorDao.updateLiquor(liquorDTO);
		if(result == 0) throw new SQLException("수정에 실패했어요.");
		return result;
	}
		
	/**
	 * 양주 삭제하기 
	 * */
	public int deleteLiquor(int liquorNo) throws SQLException{
		int result = liquorDao.deleteLiquor(liquorNo);
		if(result == 0) throw new SQLException("삭제에 실패했어요.");
		return result;
	}
	
}
