package com.life.biz;

import com.life.dao.weather_dao;
import com.life.dto.weather_dto;

public class weather_biz {
	private weather_dao dao = new weather_dao();

	public weather_dto selectOne(int board_no_seq) {
		// TODO Auto-generated method stub
		return dao.selectOne(board_no_seq);
	}

	public int update(weather_dto weather_dto) {
		// TODO Auto-generated method stub
		return dao.update(weather_dto);
	}

	public int insert(weather_dto weather_dto) {
		// TODO Auto-generated method stub
		return dao.insert(weather_dto);
	}

	public int delete(int board_no_seq) {
		// TODO Auto-generated method stub
		return dao.delete(board_no_seq);
	}
}
