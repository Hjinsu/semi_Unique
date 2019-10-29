package com.life.biz;

import java.util.List;

import com.life.dao.schedule_dao;
import com.life.dto.schedule_dto;

public class schedule_biz {
	private schedule_dao dao = new schedule_dao();
	
	public List<schedule_dto> getCalViewList(String id, String mdate) {
		return dao.getCalViewList(id, mdate);
	}
	
	public int getCalViewCount(String id, String date) {
		return dao.getCalViewCount(id, date);
	}

	public List<schedule_dto> selectList(String id, String mdate) {
		// TODO Auto-generated method stub
		return dao.selectList(id, mdate);
	}
	public int insertCalBoard(schedule_dto dto) {
		return dao.insertCalBoard(dto);
	}
	public boolean mulDel(String[] schedule_no, String[] schedule_id) {
		return dao.mulDel(schedule_no,schedule_id);
	}
	public schedule_dto selectone(int schedule_no, String schedule_id) {
		return dao.selectone(schedule_no,schedule_id);
	}
	public int del(int schedule_no,String schedule_id) {
		return dao.del(schedule_no,schedule_id);
	}

	public int update(schedule_dto dto) {
		return dao.update(dto);
	}

	public int insertBoard(int board_no_seq,String schedule_id) {
		// TODO Auto-generated method stub
		return dao.insertBoard(board_no_seq, schedule_id);
	}
}
