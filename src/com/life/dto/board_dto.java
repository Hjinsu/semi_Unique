package com.life.dto;

import java.util.Date;

public class board_dto {
	private int board_no_seq;
	private int board_group_order;
	private int board_groupno_seq;
	private int board_tab;
	private String board_writer;
	private String board_title;
	private String board_content;
	private Date board_regdate;
	private String board_location;
	private int board_viewnum;
	private String board_tag;
	private String board_genderlimit;
	private int board_age_min;
	private int board_age_max;
	private int board_peoplelimit;
	private Date board_timelimit;
	private String board_del;
	private String board_attend_user;
	
	public String getBoard_attend_user() {
		return board_attend_user;
	}
	public void setBoard_attend_user(String board_attend_user) {
		this.board_attend_user = board_attend_user;
	}
	public String getBoard_del() {
		return board_del;
	}
	public void setBoard_del(String board_del) {
		this.board_del = board_del;
	}
	public int getBoard_tab() {
		return board_tab;
	}
	public void setBoard_tab(int board_tab) {
		this.board_tab = board_tab;
	}
	public int getBoard_no_seq() {
		return board_no_seq;
	}
	public void setBoard_no_seq(int board_no_seq) {
		this.board_no_seq = board_no_seq;
	}
	public int getBoard_group_order() {
		return board_group_order;
	}
	public void setBoard_group_order(int board_group_order) {
		this.board_group_order = board_group_order;
	}
	public int getBoard_groupno_seq() {
		return board_groupno_seq;
	}
	public void setBoard_groupno_seq(int board_groupno_seq) {
		this.board_groupno_seq = board_groupno_seq;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public Date getBoard_regdate() {
		return board_regdate;
	}
	public void setBoard_regdate(Date board_regdate) {
		this.board_regdate = board_regdate;
	}
	public String getBoard_location() {
		return board_location;
	}
	public void setBoard_location(String board_location) {
		this.board_location = board_location;
	}

	public int getBoard_viewnum() {
		return board_viewnum;
	}
	public void setBoard_viewnum(int board_viewnum) {
		this.board_viewnum = board_viewnum;
	}
	
	public String getBoard_tag() {
		return board_tag;
	}
	public void setBoard_tag(String board_tag) {
		this.board_tag = board_tag;
	}
	public String getBoard_genderlimit() {
		return board_genderlimit;
	}
	public void setBoard_genderlimit(String board_genderlimit) {
		this.board_genderlimit = board_genderlimit;
	}
	public int getBoard_age_min() {
		return board_age_min;
	}
	public void setBoard_age_min(int board_age_min) {
		this.board_age_min = board_age_min;
	}
	public int getBoard_age_max() {
		return board_age_max;
	}
	public void setBoard_age_max(int board_age_max) {
		this.board_age_max = board_age_max;
	}
	public int getBoard_peoplelimit() {
		return board_peoplelimit;
	}
	public void setBoard_peoplelimit(int board_peoplelimit) {
		this.board_peoplelimit = board_peoplelimit;
	}
	public Date getBoard_timelimit() {
		return board_timelimit;
	}
	public void setBoard_timelimit(Date board_timelimit) {
		this.board_timelimit = board_timelimit;
	}
	
	
}
