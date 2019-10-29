package com.life.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.life.dto.health_dto;

public class health_dao extends health_sqlmap {
	private String namespace = "com.life.db.healthmapper.";

	public List<health_dto> selectList(String health_id) {
		SqlSession session = null;

		List<health_dto> list = new ArrayList<health_dto>();

		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList(namespace + "selectList",health_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public int insert(health_dto dto) {

		SqlSession session = null;
		int res = 0;

		System.out.println(dto.getHealth_food());
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.insert(namespace + "insert", dto);

			if (res > 0) {
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

	public List<health_dto> searchList(String health_id, String health_min_date, String health_max_date) {
		SqlSession session = null;
		List<health_dto> list = new ArrayList<health_dto>();
		Map<String, String> map = new HashMap<String, String>();

		map.put("health_id", health_id);
		map.put("health_min_date", health_min_date);
		map.put("health_max_date", health_max_date);
		try {
			session = getSqlSessionFactory().openSession(false);
			list = session.selectList(namespace + "searchList", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
