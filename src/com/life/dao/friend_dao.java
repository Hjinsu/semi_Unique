package com.life.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.life.dto.friend_dto;

public class friend_dao extends friend_sqlmap{
	private String namespace ="com.life.db.friendmapper.";

	public int insert(String member_id, String friend_id) {
		SqlSession session = null;
		
		session = getSqlSessionFactory().openSession(false);
		Map<String, String> map = new HashMap<String, String>();

		map.put("member_id", member_id);
		map.put("friend_id", friend_id);
		
		int res = 0;
		
		if(friend_id.equals(session.selectOne(namespace+"selectfriend01",member_id))) {
			res = 2;
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend02",member_id))) {
			res = 2;
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend03",member_id))) {
			res = 2;
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend04",member_id))) {
			res = 2;
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend05",member_id))) {
			res = 2;
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend06",member_id))) {
			res = 2;
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend07",member_id))) {
			res = 2;
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend08",member_id))) {
			res = 2;
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend09",member_id))) {
			res = 2;
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend10",member_id))) {
			res = 2;
		}else {
			if(session.selectOne(namespace+"selectfriend01",member_id) == null) {
				res = session.insert(namespace+"addfriend01",map);
			}else if(session.selectOne(namespace+"selectfriend02",member_id) == null) {
				res = session.insert(namespace+"addfriend02",map);
			}else if(session.selectOne(namespace+"selectfriend03",member_id) == null) {
				res = session.insert(namespace+"addfriend03",map);
			}else if(session.selectOne(namespace+"selectfriend04",member_id) == null) {
				res = session.insert(namespace+"addfriend04",map);
			}else if(session.selectOne(namespace+"selectfriend05",member_id) == null) {
				res = session.insert(namespace+"addfriend05",map);
			}else if(session.selectOne(namespace+"selectfriend06",member_id) == null) {
				res = session.insert(namespace+"addfriend06",map);
			}else if(session.selectOne(namespace+"selectfriend07",member_id) == null) {
				res = session.insert(namespace+"addfriend07",map);
			}else if(session.selectOne(namespace+"selectfriend08",member_id) == null) {
				res = session.insert(namespace+"addfriend08",map);
			}else if(session.selectOne(namespace+"selectfriend09",member_id) == null) {
				res = session.insert(namespace+"addfriend09",map);
			}else if(session.selectOne(namespace+"selectfriend10",member_id) == null) {
				res = session.insert(namespace+"addfriend10",map);
			}
			else {
				res = 3;
			}
		}
		
		
		if(res > 0)
			session.commit();
		else
			session.rollback();
		return res;
	}

	public String searchId(String friend_id) {
		SqlSession session = null;
		
		session = getSqlSessionFactory().openSession();
		
		String res = session.selectOne("searchId", friend_id);
		
		return res;
	}
	
	public friend_dto selectall(String member_id) {
		SqlSession session = null;
		friend_dto dto = new friend_dto();
		session = getSqlSessionFactory().openSession();
		
		dto = session.selectOne("selectfriendall", member_id);
		
		return dto;
	}

	public int del_friend(String member_id, String friend_id) {
		SqlSession session = null;
		
		session = getSqlSessionFactory().openSession();
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("member_id", member_id);
		map.put("friend_id", friend_id);
		
		int res = 0;
		if(friend_id.equals(session.selectOne(namespace+"selectfriend01",member_id))) {
			res = session.delete(namespace+"del_friend01",map);
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend02",member_id))) {
			res = session.delete(namespace+"del_friend02",map);
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend03",member_id))) {
			res = session.delete(namespace+"del_friend03",map);
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend04",member_id))) {
			res = session.delete(namespace+"del_friend04",map);
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend05",member_id))) {
			res = session.delete(namespace+"del_friend05",map);
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend06",member_id))) {
			res = session.delete(namespace+"del_friend06",map);
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend07",member_id))) {
			res = session.delete(namespace+"del_friend07",map);
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend08",member_id))) {
			res = session.delete(namespace+"del_friend08",map);
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend09",member_id))) {
			res = session.delete(namespace+"del_friend09",map);
		}else if(friend_id.equals(session.selectOne(namespace+"selectfriend10",member_id))) {
			res = session.delete(namespace+"del_friend10",map);
		}else
			res = 0;
		
		if(res > 0)
			session.commit();
		else
			session.rollback();
		
		return res;
	}
	
}
