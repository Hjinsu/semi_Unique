package com.life.biz;

import com.life.dao.map_dao;
import com.life.dto.map_dto;

public class map_biz {
	private map_dao dao = new map_dao();

	public map_dto selectOne(int map_no_seq) {
		return dao.selectOne(map_no_seq);
		
	}
	public int insert(map_dto dto) {
		return dao.insert(dto);
		
	}
	public int update(map_dto dto) {
		return dao.update(dto);
		
	}
	public int delete(int map_no_seq) {
		return map_no_seq;
		
	}
}
