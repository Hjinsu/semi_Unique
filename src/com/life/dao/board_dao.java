package com.life.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.life.dto.board_dto;
import com.life.dto.comment_dto;


public class board_dao extends board_sqlmap {
	private String namespace = "com.life.db.boardmapper.";

	public List<board_dto> selectlist() {

		SqlSession session = null;
		List<board_dto> list = new ArrayList<board_dto>();

		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(namespace + "selectlist");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;

	}

	public board_dto selectone(int board_no_seq) {

		SqlSession session = null;
		board_dto dto = new board_dto();

		try {
			session = getSqlSessionFactory().openSession();
			dto = session.selectOne(namespace + "selectone", board_no_seq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return dto;
	}

	public int update(board_dto dto) {
		SqlSession session = null;
		int res = 0;
		try {
			session = getSqlSessionFactory().openSession();
			res = session.update(namespace + "update", dto);

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

	public int delete(int board_no_seq) {

		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession();
			res = session.delete(namespace + "delete", board_no_seq);

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

	public int insert(board_dto dto) {
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession();
			res = session.delete(namespace + "insert", dto);

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

	public int updateviewnum(board_dto dto) {
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession();
			res = session.update(namespace + "viewnum", dto);

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



	public int insertAS(board_dto dto) {
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession();
			res = session.insert(namespace + "insertAS", dto);

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

	public int updateAS(int board_no_seq) {

		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession();
			res = session.update(namespace + "updateAS", board_no_seq);

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
	
	public List<board_dto> searchlist(Map<String, String> map){
		SqlSession session= null;
		List<board_dto> search_list = new ArrayList<board_dto>();
		
		try {
			session = getSqlSessionFactory().openSession();
			search_list = session.selectList(namespace+"selectlist",map);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return search_list;
		
	}
	
	public List<comment_dto> selectlist_comment(int board_no_seq){
		SqlSession session = null;
		List<comment_dto> comment_list = new ArrayList<comment_dto>();

		try {
			session = getSqlSessionFactory().openSession();
			comment_list = session.selectList(namespace + "selectlist_comment",board_no_seq);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return comment_list;
	}
	
	public int delete_comment(int comment_no_seq, String member_id) {
		
		SqlSession session = null;
		int res = 0;
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_id", member_id);
		map.put("comment_no_seq", comment_no_seq+"");
		try {
			session = getSqlSessionFactory().openSession();
			res = session.update(namespace + "delete_comment", map);

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
	
	public int insert_comment(comment_dto comment_dto) {
		SqlSession session = null;
		int res = 0;

		try {
			session = getSqlSessionFactory().openSession();
			res = session.insert(namespace + "insert_comment", comment_dto);

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

	public int attenduser(int board_no_seq, String attend_user) {
		// TODO Auto-generated method stub
		SqlSession session = null;
		int res = 0;
		Map<String, String> map = new HashMap<String, String>();
		map.put("board_no_seq", board_no_seq+"");
		map.put("attend_user", attend_user);

		try {
			session = getSqlSessionFactory().openSession();
			res = session.update(namespace + "attenduser", map);

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

	public String attenduser_select(int board_no_seq) {
		SqlSession session = null;
		
		String res = "";
		try {
			session = getSqlSessionFactory().openSession();
			res = session.selectOne(namespace + "attendselect",board_no_seq);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}

	public int curselect() {
		SqlSession session = null;
		
		int res = 0;
		try {
			session = getSqlSessionFactory().openSession();
			res = session.selectOne(namespace + "curselect");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return res;
	}


}
