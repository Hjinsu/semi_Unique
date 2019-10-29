package com.life.biz;

import com.life.dao.friend_dao;
import com.life.dto.friend_dto;

public class friend_biz {
	private friend_dao dao = new friend_dao();

	public int insert(String member_id, String friend_id) {
		// TODO Auto-generated method stub
		return dao.insert(member_id, friend_id);
	}

	public String searchId(String friend_id) {
		// TODO Auto-generated method stub
		return dao.searchId(friend_id);
	}

	public friend_dto selectall(String member_id) {
		// TODO Auto-generated method stub
		return dao.selectall(member_id);
	}

	public int del_friend(String member_id, String friend_id) {
		// TODO Auto-generated method stub
		return dao.del_friend(member_id, friend_id);
	}
}
