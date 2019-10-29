package com.life.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.life.dto.statistics_dto;

public class statistics_dao extends statistics_sqlmap{
	private String namespace ="com.life.db.statisticsmapper.";
	
	public int insert(statistics_dto dto) {
		
		SqlSession session = null;
		int res = 0;
		
		session = getSqlSessionFactory().openSession(true);
		
		return 0;
	}
	
	public List<statistics_dto> selectList(){
		
		SqlSession session = null;
		List<statistics_dto> list = new ArrayList<statistics_dto>();
		
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList(namespace+"selectList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
}
