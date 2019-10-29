package com.life.biz;

import com.life.dao.member_dao;
import com.life.dto.member_dto;

public class member_biz {
	private member_dao dao = new member_dao();
	
	public member_dto selectMemberinfo(String id) {
		
		return dao.selectMemberinfo(id);
	}
	
	public int updateMemberinfo(member_dto dto) {
		
		return dao.updateMemberinfo(dto);
	}
	
	public int dropoutMember(String member_id) {
		
		return dao.dropoutMember(member_id);
	}
	
	public member_dto loginChk(String member_id, String member_pw) {
		return dao.loginChk(member_id, member_pw);
	}

	public member_dto idChk(String member_id) {
		return dao.idChk(member_id);
	}
	
	public int signupChk(member_dto dto) {
		return dao.signupChk(dto);
	}

	public member_dto kakaologinChk(String member_id, String member_email) {
		return dao.kakaologinChk(member_id, member_email);
	}

	public member_dto idsearch(String m_email) {
		return dao.searchid(m_email);
	}

	public member_dto pwsearch(String id, String memail) {
		return dao.searchpw(id,memail);
	}
}
