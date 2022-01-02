package kosta.mvc.model.service;

import java.sql.SQLException;
import java.util.List;

import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dao.ReviewDAO;
import kosta.mvc.model.dao.ReviewDAOImpl;
import kosta.mvc.model.dto.Review;

public class ReviewService {
	private ReviewDAO reviewDAO = new ReviewDAOImpl();
	
	/**
	 * 전체 리뷰 조회
	 * */
	public List<Review> reviewSelectAll() throws SQLException {
		List<Review> reviewList = reviewDAO.reviewSelectAll();
		if(reviewList == null || reviewList.isEmpty()) {
			throw new SQLException("등록된 리뷰가 없습니다.");
		}
		
		return reviewList;
	}
	
	/**
	 * 고객아이디 해당 리뷰 조회
	 * */
	public List<Review> reviewSelectByCustomerId(String customerId) throws NotFoundException, SQLException{
		List<Review> list = reviewDAO.reviewSelectByCustomerId(customerId);
		if(list.size()==0)throw new NotFoundException("해당 회원아이디에 존재하는 리뷰가 없습니다.");
		return list;
	}
	
	/**
	 * 양주 종류별 리뷰 조회
	 * */
	public List<Review> reviewSelectByLiquorNo(int liquorNo) throws NotFoundException, SQLException {
		List<Review> reviewList = reviewDAO.reviewSelectByLiquorNo(liquorNo);
		if(reviewList == null) {
			throw new SQLException("해당 리뷰번호에 등록된 리뷰가 없습니다.");
		}
		
		return reviewList;
	}
	
	/**
	 * 리뷰 등록
	 * */
	public void insertReview(int liquorNo, String customerId, String content) throws SQLException {
		int result = reviewDAO.insertReview(liquorNo, customerId, content);
		if(result == 0) throw new SQLException("등록되지 않았습니다.");

	}
	
	/**
	 * 리뷰 수정
	 * */
	public void updateReview(String content, int reviewNo)throws SQLException {
		int result = reviewDAO.updateReview(content, reviewNo);
		if(result == 0) throw new SQLException("수정되지 않았습니다.");
	}
	
	/**
	 * 해당 리뷰번호에 해당하는 리뷰 삭제
	 * */
	public void deleteReview(int reviewNo) throws SQLException {
		int result = reviewDAO.deleteReview(reviewNo);
		if(result == 0) throw new SQLException("삭제되지 않았습니다.");

	}

}
