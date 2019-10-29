package com.life.dao;

import org.apache.ibatis.session.SqlSession;

import com.life.dto.map_dto;

public class map_dao extends map_sqlmap{
	private String namespace ="com.life.db.mapmapper.";

	public map_dto selectOne(int map_no_seq) {
		SqlSession session = null;
				
		map_dto dto = new map_dto();
	try {
		session = getSqlSessionFactory().openSession(false);
		dto = session.selectOne(namespace+ "selectOne",map_no_seq );
		
		
	} catch (Exception e) {
		e.printStackTrace();
		
	}finally {
		session.close();
	}
		return dto;
	}

	public int insert(map_dto dto) {
		 SqlSession session = null;
		
		 
		 int res = 0;
			
			try {
				session = getSqlSessionFactory().openSession(false);
				res = session.insert(namespace+"insert",dto);
				
				if(res > 0) {
					session.commit();
				}else {
					session.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
				
			}finally {
				session.close();
			}
		 
		return res;
	}

	public int update(map_dto dto) {

		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace+"update",dto);
			
			if(res > 0) {
				session.commit();
			}else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		return res;
	}
	
	public int delete(int map_no_seq) {

		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.insert(namespace+"delete",map_no_seq);
			if(res > 0) {
				session.commit();
			}else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		
		return res;
		
	}
	
}
