package com.life.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.life.dto.message_dto;

public class message_dao extends message_sqlmap{
	private String namespace ="com.life.db.messagemapper.";

	public message_dto selectone(String message_no, String message_receive_id) {
		SqlSession session = null;
		
		session = getSqlSessionFactory().openSession(false);
		message_dto dto = new message_dto();
		Map<String, String> map = new HashMap<String, String>();

		map.put("message_no", message_no);
		map.put("message_receive_id", message_receive_id);
		
		dto = session.selectOne(namespace+"selectone", map);
		
		session.close();
		return dto;
	}

	public List<message_dto> selectlist(String message_receive_id) {
		SqlSession session = null;
		
		List<message_dto> list = new ArrayList<message_dto>();
		session = getSqlSessionFactory().openSession();
				
		list = session.selectList(namespace + "selectlist",message_receive_id);
		
		session.close();
		
		return list;
	}

	public int insert(message_dto dto) {
		SqlSession session = null;
		
		session = getSqlSessionFactory().openSession();
				
		int res = session.insert(namespace+"insert",dto); 
		
		if(res > 0){
			session.commit();
		}
		else {
			session.rollback();
		}
		
		session.close();
		
		return res;
	}

	public boolean muldel(String[] seq, String[] message_receive_id) {
		int count = 0;
		Map<String,String[]> map = new HashMap<String,String[] >();
		map.put("message_receive_id", message_receive_id);
		map.put("seqs", seq);
		
		SqlSession session = null;
		
		session = getSqlSessionFactory().openSession(false);
		count = session.delete(namespace+"muldel", map);
			
		if(count == seq.length)
			session.commit();
		else
			session.rollback();
		
		session.close();
	
		return count == seq.length ? true : false;
	}

	public int delete(String message_receive_id, String message_no) {
		SqlSession session = null;
		
		session = getSqlSessionFactory().openSession();
		Map<String,String> map = new HashMap<String,String >();
		map.put("message_receive_id", message_receive_id);
		map.put("message_no", message_no);
		
		int res = session.delete(namespace+"delete",map); 
		
		if(res > 0){
			session.commit();
		}
		else {
			session.rollback();
		}
		
		session.close();
		
		return res;
	}
	
	public int update(String message_receive_id, String message_no) {
		SqlSession session = null;
		session = getSqlSessionFactory().openSession(false);
		Map<String, String> map = new HashMap<String, String>();
		map.put("message_receive_id", message_receive_id);
		map.put("message_no", message_no);
		
		int res = session.update(namespace+"update", map);
		
		if(res > 0){ 
			session.commit();
		}
		else {
			session.rollback();
		}
		
		session.close();
		
		return res;
	}
}
