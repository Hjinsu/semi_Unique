package com.life.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.life.dto.account_dto;

public class account_dao extends account_sqlmap{
	private String namespace ="com.life.db.accountmapper.";
	
	public int insert(account_dto dto, String account_id) {
		
		SqlSession session = null;
		int res = 0;
				
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace+"insert",dto);
			
			if(res>0) {
				session.commit();
			} else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	
	public int update(account_dto dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update(namespace+"update",dto);
			
			if(res > 0) {
				session.commit();
			} else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
 		
		return res;
	}
	
	public int delete(int account_io_seq) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete(namespace+"delete",account_io_seq);
			
			if(res > 0) {
				session.commit();
			} else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	
	public List<account_dto> selectList(String account_id) {
		
		SqlSession session = null;
		List<account_dto> list = new ArrayList<account_dto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList(namespace+"selectList",account_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	
	public account_dto selectOne(int account_totalcash) {
		SqlSession session = null;
		
		account_dto dto = new account_dto();
		
		session = getSqlSessionFactory().openSession(false);
		dto = session.selectOne(namespace+"selectOne",account_totalcash);
		
		return dto;
	}
	
	public int selectTotalCash(String account_id) {
		SqlSession session = null;
		session = getSqlSessionFactory().openSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("account_id", account_id);
		String res = session.selectOne(namespace+"selecttotalcash",map);
		int rs = 0;
		System.out.println(res);
		if(res != null) {
			System.out.println("들어오니?");
			rs = Integer.parseInt(res);
		}
		return rs;
	}

	public List<account_dto> searchList(String account_id, String account_min_date, String account_max_date) {
		SqlSession session = null;
		session = getSqlSessionFactory().openSession();
		Map<String, String> map = new HashMap<String, String>();
		List<account_dto> list = new ArrayList<account_dto>();
		
		map.put("account_id", account_id);
		map.put("account_min_date", account_min_date);
		map.put("account_max_date", account_max_date);
		
		list = session.selectList(namespace+"searchList",map);
		
		return list;
	}
}
