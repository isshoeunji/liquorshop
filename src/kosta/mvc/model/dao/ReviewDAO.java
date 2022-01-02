package kosta.mvc.model.dao;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.model.dto.Review;

public interface ReviewDAO {
	/**
	 * 전체 리뷰 조회
	 * */
	List<Review> reviewSelectAll() throws SQLException;
	
	/**
	 * 회원별 리뷰 조회
	 *  판매자용 메뉴에서 사용할 것임
	 * */
	List<Review> reviewSelectByCustomerId(String customerId) throws SQLException;
	
	/**
	 * 양주종류별(양주번호별) 리뷰 조회
	 *  판매자용 메뉴에서 사용할 것임
	 * */
	List<Review> reviewSelectByLiquorNo(int liquorNo) throws SQLException;
	
	/**
	 * 리뷰 등록
	 * */
	int insertReview(int liquorNo, String customerId, String content) throws SQLException;
	
	
	/**
	 * 리뷰 수정하기
	 * */
	int updateReview(String content, int reviewNo) throws SQLException;

	
	/**
	 * 리뷰 삭제하기  -> 리뷰 번호를 받아서
	 * 해당 리뷰번호에 해당하는 리뷰 삭제
	 * */
	int deleteReview(int reviewNo) throws SQLException;

	
	
	
	
}
