package com.life.biz;

import java.util.List;

import com.life.dao.account_dao;
import com.life.dto.account_dto;

public class account_biz {
	private account_dao dao = new account_dao();
	
	public List<account_dto> selectList(String account_id) {
		return dao.selectList(account_id);
	}
	
	public int insert(account_dto dto, String account_id) {
		dto.setAccount_totalcash(dto.getAccount_totalcash() + dto.getAccount_in_cash());
		dto.setAccount_totalcash(dto.getAccount_totalcash() - dto.getAccount_out_cash());
		return dao.insert(dto, account_id);
	}
	
	public int update(account_dto dto) {
		return dao.update(dto);
	}
	
	public int delete(int account_io_seq) {
		return dao.delete(account_io_seq);
	}
	
	public account_dto selectOne(int account_totalcash) {
		return dao.selectOne(account_totalcash);
	}
	public int selectTotalCash(String account_id) {
		return dao.selectTotalCash(account_id);
	}
	
	public List<account_dto> searchList(String account_id, String account_min_date, String account_max_date) {
		return dao.searchList(account_id, account_min_date, account_max_date);
	}
	
}
