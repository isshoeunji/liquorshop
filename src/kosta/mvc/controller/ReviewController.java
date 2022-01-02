package kosta.mvc.controller;

import java.util.List;

import kosta.mvc.model.dto.Review;
import kosta.mvc.model.service.ReviewService;
import kosta.mvc.view.EndView;
import kosta.mvc.view.FailView;

public class ReviewController {
	private static ReviewService reviewService = new ReviewService();
	
	/**
	 * 전체 리뷰 검색
	 * */
	public static void reviewSelectAll() {
		try {
			List<Review> reviewList = reviewService.reviewSelectAll();
			EndView.printReivew(reviewList);
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
			
		}
	}
	
	/**
	 * 고객아이디 해당 리뷰 조회
	 * */
	public static void reviewSelectByCustomerId(String customerId) {
		try {
			List<Review> reviewList = reviewService.reviewSelectByCustomerId(customerId);
			EndView.printReviewByCustomerId(reviewList);
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
			
		}
	}
	
	/**
	 * 양주종류별 리뷰 검색
	 * */
	public static void reviewSelectByLiquorNo(int liquorNo) {
		try {
			List<Review> reviewList = reviewService.reviewSelectByLiquorNo(liquorNo);
			EndView.printReviewByLiquorNo(reviewList);
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
			
		}
	}
	
	/**
	 * 리뷰 등록
	 * */
	public static void insertReview (int liquorNo, String customerId, String content) {
		try {
			reviewService.insertReview(liquorNo, customerId, content);
			EndView.meesegePrint("등록되었습니다.");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 리뷰 수정하기
	 * */
	public static void updateReview (String content, int reviewNo) {
		try {
			reviewService.updateReview(content, reviewNo);
			EndView.meesegePrint("수정되었습니다.");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 리뷰 삭제
	 * */
	public static void deleteReview (int reviewNo) {
		try {
			reviewService.deleteReview(reviewNo);
			EndView.meesegePrint("삭제되었습니다.");
		}catch(Exception e) {
			FailView.errorMessage(e.getMessage());
		}
	}
}
