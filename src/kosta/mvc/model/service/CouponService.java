package kosta.mvc.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kosta.mvc.exception.DuplicatedException;
import kosta.mvc.exception.NotFoundException;
import kosta.mvc.model.dao.CouponDAO;
import kosta.mvc.model.dao.CouponDAOImpl;
import kosta.mvc.model.dto.Coupon;

public class CouponService {
	CouponDAO couponDAO = new CouponDAOImpl();
	
	/**
	 * 쿠폰 리스트 보여주기
	 * */
	public List<Coupon>  couponSelectAll(String customerId) throws SQLException, NotFoundException {
		List<Coupon> couponlist = couponDAO.couponSelectAll(customerId);
		if(couponlist.size() == 0) throw new NotFoundException("쿠폰이 없습니다");
		return couponlist;
	}
	
	/**
	 * 사용할 쿠폰 보여주기
	 * */
	public Coupon couponSelectByCouponNo(String customerId, int couponNo) throws SQLException, NotFoundException {
		Coupon coupon = couponDAO.couponSelectByCouponNo(customerId, couponNo);
		if(coupon == null) throw new NotFoundException(couponNo + "번에 해당하는 쿠폰은 존재하지 않습니다");
		return coupon;
	}
	
	/**
	 * 쿠폰테이블 등록하기
	 * */
	public void insertCouponTable(String customerId, int salePercent) throws SQLException, NotFoundException, DuplicatedException {
		int result = couponDAO.insertCouponTable(customerId, salePercent);
		if(result == 0) throw new NotFoundException("등록되지 않았습니다");
		if(result == -10) throw new DuplicatedException();
	}
	
	/**
	 * 쿠폰 사용하기
	 * */
	public void useCoupon(int couponNo) throws SQLException, NotFoundException {
		if (couponNo != 0) {
			int result = couponDAO.useCoupon(couponNo);
			if (result == 0)
				throw new NotFoundException("사용되지 않았습니다");
		}
	}
}
