package com.life.biz;

import java.util.List;

import com.life.dao.message_dao;
import com.life.dto.message_dto;

public class message_biz {
	private message_dao dao = new message_dao();
	
	public message_dto selectone(String message_no, String message_receive_id) {
		return dao.selectone(message_no, message_receive_id);
	}
	
	public List<message_dto> selectlist(String message_receive_id){
		return dao.selectlist(message_receive_id);
	}
	
	public int insert(message_dto dto) {
		return dao.insert(dto);
	}

	public boolean muldel(String[] seq, String[] message_receive_id) {
		return dao.muldel(seq, message_receive_id);
	}

	public int delete(String message_receive_id, String message_no) {
		// TODO Auto-generated method stub
		return dao.delete(message_receive_id, message_no);
	}
	public int update(String message_receive_id, String message_no) {
		return dao.update(message_receive_id, message_no);
	}
}
