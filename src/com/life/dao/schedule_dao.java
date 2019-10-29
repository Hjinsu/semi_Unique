package com.life.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.life.dto.schedule_dto;

public class schedule_dao extends schedule_sqlmap{
	private String namespace ="com.life.db.schedulemapper.";
	
	public List<schedule_dto> getCalViewList(String id, String mdate){
		SqlSession session = null;
		
		List<schedule_dto> list = new ArrayList<schedule_dto>();
		session = getSqlSessionFactory().openSession();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("mdate", mdate);
		
		list = session.selectList(namespace + "selectthreelist",map);
		
		session.close();
		
		return list;
	}

	public int getCalViewCount(String id, String date) {
		SqlSession session = null;
				
		session = getSqlSessionFactory().openSession();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("mdate", date);
		
		int res = session.selectOne(namespace+"selectcount",map);
		
		session.close();
		return res;
	}

	public List<schedule_dto> selectList(String id, String mdate) {
		SqlSession session = null;
		
		session = getSqlSessionFactory().openSession();
		
		List<schedule_dto> list = new ArrayList<schedule_dto>();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("mdate", mdate);
		
		list = session.selectList(namespace + "selectlist",map);
		
		session.close();
		
		return list;
	}

	public int insertCalBoard(schedule_dto dto) {
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
	
	public boolean mulDel(String[] schedule_no, String[] schedule_id) {
		int count = 0;
		Map<String,String[]> map = new HashMap<String,String[] >();
		
		map.put("seqs", schedule_no);
		map.put("schedule_id",schedule_id);
		
		SqlSession session = null;
		
		session = getSqlSessionFactory().openSession(false);
		count = session.delete(namespace+"muldel", map);
			
		if(count == schedule_no.length)
			session.commit();
		else
			session.rollback();
		
		session.close();
	
		return count == schedule_no.length ? true : false;
	}

	public schedule_dto selectone(int schedule_no, String schedule_id) {
		SqlSession session = null;
		
		session = getSqlSessionFactory().openSession(false);
		schedule_dto dto = new schedule_dto();
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("schedule_no", schedule_no+"");
		map.put("schedule_id",schedule_id);
		dto = session.selectOne(namespace+"selectone",map);
		
		session.close();
		return dto;
	}

	public int del(int schedule_no, String schedule_id) {
		
		SqlSession session = null;
		session = getSqlSessionFactory().openSession(false);
		Map<String,String> map = new HashMap<String,String>();
		
		map.put("schedule_no", schedule_no+"");
		map.put("schedule_id",schedule_id);
		int res = session.delete(namespace+"del", map);
		
		if(res >0) {
			session.commit();
		}else {
			session.rollback();
		}
		
		return res;
	}

	public int update(schedule_dto dto) {
		SqlSession session = null;
		session = getSqlSessionFactory().openSession();
		
		int res = session.update(namespace+"update",dto);
		
		if(res > 0)
			session.commit();
		else
			session.rollback();
		return res;
	}

	public int insertBoard(int board_no_seq, String schedule_id) {
		SqlSession session = null;
		session = getSqlSessionFactory().openSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("board_no_seq", board_no_seq+"");
		map.put("schedule_id", schedule_id);
		int res = session.insert(namespace+"insertboard",map);
		
		if(res > 0)
			session.commit();
		else
			session.rollback();
		
		return res;
	}
}
