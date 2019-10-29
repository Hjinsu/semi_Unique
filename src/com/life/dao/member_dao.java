package com.life.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.life.dto.member_dto;

public class member_dao extends member_sqlmap {
	private String namespace = "com.life.db.membermapper.";

	public member_dto loginChk(String id, String pw) {
		SqlSession session = null;
		member_dto dto = null;

		Map<String, String> map = new HashMap<>();

		map.put("member_id", id);
		map.put("member_pw", pw);

		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace + "loginChk", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dto;
	}

	public member_dto idChk(String id) {
		SqlSession session = null;
		member_dto dto = null;

		Map<String, String> map = new HashMap<>();

		map.put("member_id", id);

		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace + "idChk", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dto;
	}

	public int signupChk(member_dto dto) {
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace + "signupChk", dto);

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

	public member_dto kakaologinChk(String id, String email) {
		SqlSession session = null;
		member_dto dto = null;

		Map<String, String> map = new HashMap<>();

		map.put("member_id", id);
		map.put("member_email", email);

		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace + "kakaologinChk", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dto;
	}

	public member_dto selectMemberinfo(String id) {

		member_dto dto = new member_dto();

		SqlSession session = null;

		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace + "selectMemberinfo", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return dto;

	}

	public int updateMemberinfo(member_dto dto) {

		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update(namespace + "updateMemberinfo", dto);

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

	public int dropoutMember(String id) {

		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update(namespace + "dropout", id);

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

	public member_dto searchid(String email) {
		SqlSession session = null;
		member_dto dto = null;
		
		Map<String, String> map = new HashMap<>();

		map.put("member_email", email);
		
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace + "searchid", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dto;
	}

	public member_dto searchpw(String id, String memail) {
		SqlSession session = null;
		member_dto dto = null;
		
		Map<String, String> map = new HashMap<>();

		map.put("member_id", id);
		map.put("member_email", memail);
		
		try {
			session = getSqlSessionFactory().openSession(false);
			dto = session.selectOne(namespace + "searchpw", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dto;
	}
}
