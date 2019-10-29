package com.life.biz;

import java.util.List;
import java.util.Map;

import com.life.dao.board_dao;
import com.life.dto.board_dto;
import com.life.dto.comment_dto;

public class board_biz {
	private board_dao dao = new board_dao(); 
	
	public List<board_dto> selectlist(){
		return dao.selectlist();
	}
	
	public board_dto selectone(int board_no_seq) {
		return dao.selectone(board_no_seq);
	}
	
	public int update(board_dto dto) {
		return dao.update(dto);
	}
	
	public int delete(int board_no_seq) {
		return dao.delete(board_no_seq);
	}
	
	public int insert(board_dto dto) {
		return dao.insert(dto);
	}
	
	public int updateviewnum(board_dto dto) {
		return dao.updateviewnum(dto);
	}
	
	 public int insertAS(board_dto dto) {
		 return dao.insertAS(dto);
	 }
	 
	 public int updateAS(int board_no_seq) {
		 
		 return dao.updateAS(board_no_seq);
	 }
	 
	 public List<board_dto> searchlist(Map<String, String> map){
		 return dao.searchlist(map);
	 }
	 
	 public List<comment_dto> selectlist_comment(int board_no_seq){
		 return dao.selectlist_comment(board_no_seq);
	 }
	 
	 public int delete_comment(int comment_no_seq, String member_id) {
		 return dao.delete_comment(comment_no_seq, member_id);
	 }
	 
	 public int insert_comment(comment_dto comment_dto) {
		 return dao.insert_comment(comment_dto);
	 }

	public int attenduser(int board_no_seq, String attend_user) {
		// TODO Auto-generated method stub
		return dao.attenduser(board_no_seq, attend_user);
	}

	public String attenduser_select(int board_no_seq) {
		// TODO Auto-generated method stub
		return dao.attenduser_select(board_no_seq);
	}

	public int curselect() {
		// TODO Auto-generated method stub
		return dao.curselect();
	}
	 
	 
}
