package com.life.dao;

import org.apache.ibatis.session.SqlSession;

import com.life.dto.weather_dto;

public class weather_dao extends weather_sqlmap{
	private String namespace ="com.life.db.weathermapper.";

	public weather_dto selectOne(int board_no_seq) {
		SqlSession session = null;
		
		weather_dto dto = new weather_dto();
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace+ "selectOne",board_no_seq );
		} catch (Exception e) {
			e.printStackTrace();
		
		}finally {
			session.close();
		}
		
		return dto;
	}

	public int update(weather_dto weather_dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace+"update",weather_dto);
			
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

	public int insert(weather_dto weather_dto) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace+"insert",weather_dto);
				
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

	public int delete(int board_no_seq) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.insert(namespace+"delete",board_no_seq);
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
