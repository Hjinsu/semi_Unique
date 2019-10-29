package com.life.biz;

import java.util.List;

import com.life.dao.health_dao;
import com.life.dto.health_dto;

public class health_biz {
	private health_dao dao = new health_dao();
	
	public int insert(health_dto dto) {
		return dao.insert(dto);
	}
	
	public List<health_dto> selectList(String id) {
		return dao.selectList(id);
	}
	
	public List<health_dto> searchList(String health_id, String health_min_date, String health_max_date) {
		return dao.searchList(health_id, health_min_date, health_max_date);
	}
	
	
}
